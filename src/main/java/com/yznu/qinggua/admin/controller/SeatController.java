package com.yznu.qinggua.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yznu.qinggua.admin.entity.Seat;
import com.yznu.qinggua.admin.service.ISeatService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huling
 * @since 2020-12-23
 */
@Api(tags = "座位操作接口")
@RestController
@RequestMapping("/admin")
public class SeatController {
    @Autowired
    ISeatService iSeatService;

    /**
     * 根据播放厅获取座位列表
     * @param rid
     * @return
     * */
    @ApiOperation(value = "根据播放厅获取座位列表")
    @GetMapping("/seat/{rid}")
    public Result getSeatsByRid(@PathVariable int rid) {
        try{
            // 查询条件 rid=#{rid}
            QueryWrapper<Seat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("rid", rid);
            List<Seat> seats = iSeatService.list();
            if(seats.size() != 0){
                return ResponseUtil.success(seats, 200, "获取座位列表成功!");
            }else{
                return ResponseUtil.error(400, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 新增座位
     * @param seat
     * @retrun
     * */
    @ApiOperation(value = "新增座位")
    @PostMapping("/seat")
    public Result addSeat(@RequestBody Seat seat) {
        try{
            boolean flag = iSeatService.save(seat);
            if(flag){
                return ResponseUtil.success(seat, 200, "成功添加一个座位!");
            }else{
                return ResponseUtil.error(500, "添加座位失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID删除座位
     * @param id
     * @return
     * */
    @ApiOperation(value = "根据ID删除座位")
    @DeleteMapping("/seat/{id}")
    public Result deleteSeatById(@PathVariable int id) {
        try{
            boolean flag = iSeatService.removeById(id);
            if(flag){
                return ResponseUtil.success(200, "删除成功!");
            }else{
                return ResponseUtil.error(500, "删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 批量删除座位
     * @param ids
     * @return
     * */
    @ApiOperation(value = "批量删除座位")
    @DeleteMapping("/seat")
    public Result deleteSeats(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iSeatService.removeByIds(ids);
            if(flag){
                return ResponseUtil.success(200, "批量删除成功!");
            }else{
                return ResponseUtil.error(500, "批量删除失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

}
