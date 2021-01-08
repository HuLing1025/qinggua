package com.yznu.qinggua.controller.admin;

import com.yznu.qinggua.pojo.Filminfo;
import com.yznu.qinggua.service.IFilmInfoService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "后台电影管理接口")
@RestController
@RequestMapping("/cms/film")
public class CmsFilmInfoController {
    @Autowired
    IFilmInfoService iFilmInfoService;

    @ApiOperation(value = "获取全部电影")
    @GetMapping("/")
    public Result getAllFilm() {
        return iFilmInfoService.getFilmList();
    }

    @ApiOperation(value = "搜索电影", notes = "匹配标题、导演、演员")
    @PostMapping("/{search}")
    public Result searchFilms(@PathVariable String search) {
        return iFilmInfoService.searchFilm(search);
    }

    @ApiOperation(value = "添加电影")
    @PostMapping("/")
    public Result addFilm(@RequestBody Filminfo filminfo) {
        return iFilmInfoService.addFilm(filminfo);
    }

    @ApiOperation(value = "修改电影信息")
    @PostMapping("/update")
    public Result updateFilmInfo(@RequestBody Filminfo filminfo) {
        return iFilmInfoService.updateFilmInfo(filminfo);
    }

    @ApiOperation(value = "根据ID删除电影")
    @DeleteMapping("/{id}")
    public Result deleteFilmById(@PathVariable int id) {
        return iFilmInfoService.deleteFilmById(id);
    }

    @ApiOperation(value = "批量删除电影", notes = "参数传json对象,其中包含ids(Array类型)")
    @DeleteMapping("/")
    public Result deleteFilms(@RequestBody Map<String, Object> params) {
        return iFilmInfoService.deleteFilms((List<Integer>)params.get("ids"));
    }

}
