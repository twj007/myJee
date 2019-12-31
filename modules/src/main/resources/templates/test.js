
function computeDate(target){
    var now = new Date();
    var stringVal = now.toLocaleDateString(/\//g, '-');
    var year = Number(stringVal.substring(0, 4));
    var month = Number(stringVal.substring(6, 8));
    var day = Number(stringVal.substring(10, 12));
    var t_year = Number(target.substring(0, 4));
    var t_month = Number(target.substring(6, 8));
    var t_day = Number(target.substring(10, 12));
    var r_day, r_month, r_year;

    // 调换
    if(year < t_year){
        console.log('switch date');
    }
    if(year == t_year){
        if(month < t_month){
            console.log('switch month');
        }
        else if(month == t_month){
            if(day < t_day){
                console.log('switch day');
            }
        }
    }

    if(day >= t_day){
        r_day = day - t_day;
    }
    else{
        // 获取上个月的天数
        var l_days = new Date(now.getFullYear(), now.getMonth(), 0).getDate();
        day += l_days;
        r_day = day - t_day;
        month -= 1;
    }

    if(month >= t_month){
        r_month = month - t_month;
    }
    else{
        month += 12;
        r_month = month - t_month;
        year -= 1;
    }

    if(year >= t_year){
        r_year = year - t_year;
    }

    return r_year + '-' + r_month + '-' + r_day;
}
