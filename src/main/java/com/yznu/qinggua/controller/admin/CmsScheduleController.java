package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.pojo.Schedule;
import com.yznu.qinggua.service.IScheduleService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Api(tags = "后台放映操作接口")
@RestController
@RequestMapping("/cms/schedule")
public class CmsScheduleController {
    @Autowired
    IScheduleService iScheduleService;

    @ApiOperation(value = "查询放映列表")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @GetMapping("")
    public Result getScheduleList() {
        return iScheduleService.getScheduleList();
    }

    @ApiOperation(value = "新增放映")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PostMapping("")
    public Result addSchedule(@RequestBody Schedule schedule) {
        return iScheduleService.addOne(schedule);
    }

    @ApiOperation(value = "修改放映")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @PutMapping("")
    public Result updateSchedule(@RequestBody Schedule schedule) {
        return iScheduleService.updateOne(schedule);
    }

    @ApiOperation(value = "根据ID删除放映")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("/{id}")
    public Result deleteScheduleById(@PathVariable int id) {
        return iScheduleService.deleteOne(id);
    }

    @ApiOperation(value = "批量删除放映")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "登录令牌", required = true)
    @DeleteMapping("")
    public Result deleteSchedules(@RequestBody Map<String, Object> params) {
        return iScheduleService.deleteSchedules((List<Integer>) params.get("ids"));
    }

}
