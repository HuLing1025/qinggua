package com.yznu.qinggua.admin.controller;

import com.yznu.qinggua.admin.entity.Filminfo;
import com.yznu.qinggua.admin.service.IFilminfoService;
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
public class FilminfoController {
    @Autowired
    IFilminfoService iFilminfoService;

    /**
     * 获取电影列表
     * @retrun
     */
    @GetMapping("/filminfo")
    public Result getFilminfoList() {
        try{
            List<Filminfo> filminfos = iFilminfoService.list();
            if(filminfos.size() != 0){
                return ResponseUtil.success(filminfos, 200, "获取电影列表成功!");
            }else{
                return ResponseUtil.success(400, "列表为空!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID获取电影
     * @param id
     * @return
     * */
    @GetMapping("/filminfo/{id}")
    public Result getFilminfoById(@PathVariable int id) {
        try{
            Filminfo filminfo = iFilminfoService.getById(id);
            if(filminfo != null){
                return ResponseUtil.success(filminfo, 200, "成功!");
            }else{
                return ResponseUtil.success(400, "未找到该电影!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 添加电影
     * @param filminfo
     * @return
     * */
    @PostMapping("/filminfo")
    public Result addFilminfo(@RequestBody Filminfo filminfo) {
        try{
            boolean flag = iFilminfoService.save(filminfo);
            if(flag){
                return ResponseUtil.success(filminfo, 200, "成功添加一部电影!");
            }else{
                return ResponseUtil.error(500, "添加电影失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 修改电影信息
     * @param filminfo
     * @return
     * */
    @PutMapping("/filminfo")
    public Result updateFilminfo(@RequestBody Filminfo filminfo) {
        try{
            boolean flag = iFilminfoService.updateById(filminfo);
            if(flag){
                return ResponseUtil.success(200, "修改成功!");
            }else{
                return ResponseUtil.success(500, "修改失败!");
            }
        }catch (Exception e){
            return ResponseUtil.error(500, "错误:" + e);
        }
    }

    /**
     * 根据ID删除电影
     * @param id
     * @return
     * */
    @DeleteMapping("/filminfo/{id}")
    public Result deleteFilminfoById(@PathVariable int id) {
        try{
            boolean flag = iFilminfoService.removeById(id);
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
     * 批量删除电影
     * @param ids
     * @return
     * */
    @DeleteMapping("/filminfo")
    public Result deleteFilminfos(@RequestBody List<Integer> ids) {
        try{
            boolean flag = iFilminfoService.removeByIds(ids);
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
