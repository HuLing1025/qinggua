package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.Schedule;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface IScheduleService {
    /**
     * 获取全部放映计划
     * @return
     * */
    Result getScheduleList();

    /**
     * 根据电影ID获取放映计划
     * @param fid
     * @return
     * */
    Result getSchedulesByFid(int fid);

    /**
     * 根据ID获取放映计划
     * @param id
     * @return
     * */
    Result getScheduleById(int id);

    /**
     * 新增放映计划
     * @param schedule
     * @return
     * */
    Result addOne(Schedule schedule);

    /**
     * 修改放映计划
     * @param schedule
     * @return
     * */
    Result updateOne(Schedule schedule);

    /**
     * 根据ID删除放映计划
     * @param id
     * @return
     * */
    Result deleteOne(int id);

    /**
     * 批量删除放映计划
     * @param ids
     * @return
     * */
    Result deleteSchedules(List<Integer> ids);
}
