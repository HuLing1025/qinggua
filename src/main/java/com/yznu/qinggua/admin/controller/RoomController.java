package com.yznu.qinggua.admin.controller;

import com.yznu.qinggua.admin.entity.Room;
import com.yznu.qinggua.admin.service.IRoomService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
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
@RestController
@RequestMapping("/admin")
public class RoomController {
    @Autowired
    IRoomService iRoomService;

    /**
     * 获取播放厅列表
     * @return
     */
    @GetMapping("/room")
    public Result getRoomList() {
        try{
            List<Room> rooms = iRoomService.list();
            if(rooms.size() != 0){
                return ResponseUtil.success(rooms, 200, "获取播放厅列表成功!");
            }else{
                return ResponseUtil.error(400, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID获取播放厅
     * @param id
     * @return
     * */
    @GetMapping("/room/{id}")
    public  Result getRoom(@PathVariable int id) {
        try{
            Room room = iRoomService.getById(id);
            if(room != null){
                return ResponseUtil.success(room, 200, "成功!");
            }else{
                return ResponseUtil.error(400, "未找到该播放厅!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 新增播放厅
     * @param room
     * @return
     * */
    @PostMapping("/room")
    public Result addRoom(@RequestBody Room room) {
        try{
            boolean flag = iRoomService.save(room);
            if(flag){
                return ResponseUtil.success(room, 200, "成功添加一个播放厅!");
            }else{
                return ResponseUtil.success(500, "添加播放厅失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 修改播放厅
     * @param room
     * @return
     * */
    @PutMapping("/room")
    public Result updateRoom(@RequestBody Room room) {
        try{
            boolean flag = iRoomService.updateById(room);
            if(flag){
                return ResponseUtil.success(200, "修改成功!");
            }else{
                return ResponseUtil.error(500, "修改失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID删除播放厅
     * @param id
     * @return
     * */
    @DeleteMapping("/room/{id}")
    public Result deleteRoomById(@PathVariable int id) {
        try{
            boolean flag = iRoomService.removeById(id);
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
     * 批量删除播放厅
     * @param ids
     * @return
     * */
    @DeleteMapping("/room")
    public Result deleteRooms(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iRoomService.removeByIds(ids);
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
