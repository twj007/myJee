package com.modules.system.service.impl;

import com.common.model.ActivityModel;
import com.common.model.ActivityModelDetail;
import com.modules.system.dao.TestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/19
 **/
@Service
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;


    @Transactional(rollbackFor = Exception.class, propagation =  Propagation.REQUIRED)
    public Long addActivity(ActivityModel model){
        return testDao.saveActivity(model);
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void cutMoney() {
        Long count = 0L;
        List<ActivityModel> models = testDao.getActivities();
        Long deleteCount = testDao.deleteTempDate();
        log.info("【quartz job】删除临时数据 :{} 条", deleteCount);
        for(ActivityModel model : models){
            List<ActivityModelDetail> details = createDetail(model);
            Long num = testDao.saveToTempTable(details);
            log.info("【quartz job】 插入明细信息：{}条", num);
            count += num;
        }
        log.info("【quartz job】 job执行完成，总共分摊出：{}条数据", count);
    }

    private List<ActivityModelDetail> createDetail(ActivityModel model){
        List<ActivityModelDetail> details = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(model.getStartDate());
        endCalendar.setTime(model.getEndDate());
        // 1. 计算平均费用

        // 跨越的年份
        int yearDay = 0;
        if(startCalendar.get(Calendar.YEAR) != endCalendar.get(Calendar.YEAR)){
            yearDay = ( endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR) ) * startCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        // 开始时的天数
        int startDay = startCalendar.get(Calendar.DAY_OF_YEAR);
        // 结束时的天数
        int endDay = endCalendar.get(Calendar.DAY_OF_YEAR);
        int totalDays = (endDay + yearDay) - startDay + 1;
        log.info("total day :{}", totalDays);
        BigDecimal avgMoney = model.getTotalMoney().divide(new BigDecimal(totalDays), 2, BigDecimal.ROUND_HALF_UP);
        log.info("平均 {}元/天", avgMoney.doubleValue());

        // 2， 计算分割日期

        int yearMonth = 0;
        if(startCalendar.get(Calendar.YEAR) != endCalendar.get(Calendar.YEAR)){
            yearMonth = (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR) ) * 12;
        }
        int startMonth = startCalendar.get(Calendar.MONTH);
        int endMonth = endCalendar.get(Calendar.MONTH);
        int monthTotal = endMonth - startMonth + 1 + yearMonth;
        if(monthTotal == 1){
            ActivityModelDetail detailTop = new ActivityModelDetail();
            detailTop.setPlanId(model.getId());
            detailTop.setFromDay(startCalendar.get(Calendar.DAY_OF_MONTH));
            detailTop.setToDay(startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            detailTop.setYear(startCalendar.get(Calendar.YEAR));
            detailTop.setMonth(startMonth);
            BigDecimal startMoney = new BigDecimal(detailTop.getToDay() - detailTop.getFromDay() + 1).multiply(avgMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
            detailTop.setMoney(startMoney.setScale(4, BigDecimal.ROUND_HALF_UP));
            details.add(detailTop);
        }else{
            ActivityModelDetail detailTop = new ActivityModelDetail();
            detailTop.setPlanId(model.getId());
            detailTop.setFromDay(startCalendar.get(Calendar.DAY_OF_MONTH));
            detailTop.setToDay(startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            detailTop.setYear(startCalendar.get(Calendar.YEAR));
            detailTop.setMonth(startMonth);
            BigDecimal startMoney = new BigDecimal(detailTop.getToDay() - detailTop.getFromDay() + 1).multiply(avgMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
            detailTop.setMoney(startMoney.setScale(4, BigDecimal.ROUND_HALF_UP));
            details.add(detailTop);
            for(int i = 1; i < monthTotal - 1; i++){
                ActivityModelDetail detail = new ActivityModelDetail();
                detail.setPlanId(model.getId());
                startCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) + 1);
                detail.setYear(startCalendar.get(Calendar.YEAR));
                detail.setFromDay(startCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                detail.setToDay(startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                detail.setMonth(startCalendar.get(Calendar.MONTH));
                BigDecimal money = new BigDecimal(detail.getToDay() - detail.getFromDay() + 1).multiply(avgMoney);
                detail.setMoney(money.setScale(4, BigDecimal.ROUND_HALF_UP));
                details.add(detail);
            }
            ActivityModelDetail detailBottom = new ActivityModelDetail();
            detailBottom.setPlanId(model.getId());
            detailBottom.setFromDay(endCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            detailBottom.setYear(endCalendar.get(Calendar.YEAR));
            detailBottom.setToDay(endCalendar.get(Calendar.DAY_OF_MONTH));
            detailBottom.setMonth(endCalendar.get(Calendar.MONTH));
            BigDecimal endMoney = new BigDecimal(detailBottom.getToDay() - detailBottom.getFromDay() + 1).multiply(avgMoney);
            detailBottom.setMoney(endMoney.setScale(4, BigDecimal.ROUND_HALF_UP));
            details.add(detailBottom);
        }
        return details;
    }

    public static void main(String[] args) {
        ActivityModel model = new ActivityModel();
        model.setStartDate(Date.from(LocalDateTime.of(2019, 10, 1, 0, 0, 0).toInstant(ZoneOffset.UTC)));
        model.setEndDate(Date.from(LocalDateTime.of(2019, 12, 30, 0, 0, 0).toInstant(ZoneOffset.UTC)));
        model.setTotalMoney(new BigDecimal(1000));
        model.setCreateDate(new Date());
        model.setId(1L);
        List<ActivityModelDetail> details = new TestService().createDetail(model);
        details.forEach(p -> {
            System.out.println(p);
        });

    }

    @Transactional(readOnly = true)
    public List<ActivityModelDetail> getActivityModelDetail(ActivityModelDetail d) {

        List<ActivityModelDetail> details = testDao.getActivityDetails(d);
        return details;
    }
}