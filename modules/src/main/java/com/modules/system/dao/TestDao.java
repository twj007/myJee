package com.modules.system.dao;

import com.common.model.ActivityModel;
import com.common.model.ActivityModelDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    List<ActivityModel> getActivities();

    Long saveToTempTable(List<ActivityModelDetail> details);

    Long deleteTempDate();

    List<ActivityModelDetail> getActivityDetails(ActivityModelDetail d);

    Long saveActivity(ActivityModel model);
}
