package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.IScheduleDao;
import com.yznu.qinggua.pojo.Schedule;
import com.yznu.qinggua.service.IScheduleService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    IScheduleDao iScheduleDao;

    @Override
    public Result getScheduleList() {
        try {
            // 获取列表
            List<Map<String, Object>> schedules = iScheduleDao.selectScheduleList();
            if (schedules.size() != 0) {
                return ResponseUtil.success(schedules, 200, "获取放映计划列表成功!");
            }
            return ResponseUtil.error(400, "放映计划列表为空!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "获取放映计划列表失败,异常: " + e);
        }
    }

    @Override
    public Result getSchedulesByFid(int fid) {
        try {
            // 根据电影ID获取放映列表
            List<Map<String, Object>> schedules = iScheduleDao.selectSchedulesByFid(fid);
            if (schedules.size() != 0) {
                return ResponseUtil.success(schedules, 200, "根据电影ID获取放映计划列表成功!");
            }
            return ResponseUtil.error(400, "该电影没有放映计划!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据电影ID获取放映计划列表失败,异常: " + e);
        }
    }

    @Override
    public Result getScheduleById(int id) {
        try {
            // 根据ID获取
            Map<String, Object> schedule = iScheduleDao.selectScheduleById(id);
            if (schedule != null) {
                return ResponseUtil.success(schedule, 200, "根据ID获取放映成功!");
            }
            return ResponseUtil.error(400, "没有找到该放映计划!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据ID获取放映失败,异常: " + e);
        }
    }

    @Override
    public Result addOne(Schedule schedule) {
        try {
            // 新增放映计划
            if (iScheduleDao.insertOne(schedule) == 1) {
                return ResponseUtil.success(200, "新增成功!");
            }
            return ResponseUtil.error(400, "新增失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "新增失败,异常: " + e);
        }
    }

    @Override
    public Result updateOne(Schedule schedule) {
        try {
            // 更新放映计划
            if (iScheduleDao.updateOne(schedule) == 1) {
                return ResponseUtil.success(200, "更新成功!");
            }
            return ResponseUtil.error(400, "更新失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "更新失败,异常: " + e);
        }
    }

    @Override
    public Result deleteOne(int id) {
        try {
            // 根据ID删除
            if (iScheduleDao.deleteScheduleById(id) == 1) {
                return ResponseUtil.success(200, "根据ID删除成功!");
            }
            return ResponseUtil.error(400, "根据ID删除失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "根据ID删除失败,异常: " + e);
        }
    }

    @Override
    public Result deleteSchedules(List<Integer> ids) {
        try {
            // 批量删除
            if (iScheduleDao.deleteSchedules(ids) == ids.size()) {
                return ResponseUtil.success(200, "批量删除成功!");
            }
            return ResponseUtil.error(400, "批量删除失败!");
        }catch (Exception e) {
            return ResponseUtil.error(500, "批量删除失败,异常: " + e);
        }
    }
}
