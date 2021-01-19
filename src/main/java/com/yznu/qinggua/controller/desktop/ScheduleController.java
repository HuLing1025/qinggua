package com.yznu.qinggua.controller.desktop;

import com.yznu.qinggua.service.IScheduleService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "客户端放映操作接口")
@RestController
@RequestMapping("/desktop/schedule")
public class ScheduleController {
    @Autowired
    IScheduleService iScheduleService;

    @ApiOperation(value = "根据电影ID获取放映列表")
    @GetMapping("/fid/{fid}")
    public Result getSchedulesByFid(@PathVariable int fid) {
        return iScheduleService.getSchedulesByFid(fid);
    }

    @ApiOperation(value = "根据ID获取放映")
    @GetMapping("/id/{id}")
    public Result getScheduleById(@PathVariable int id) {
        return iScheduleService.getScheduleById(id);
    }

}
