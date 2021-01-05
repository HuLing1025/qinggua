package com.yznu.qinggua.user.controller;

import com.yznu.qinggua.user.pojo.Filminfo;
import com.yznu.qinggua.user.service.IFilmInfoService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "电影操作接口")
@RestController
@RequestMapping("/film")
public class FilmInfoController {
    @Autowired
    IFilmInfoService iFilmInfoService;

    @ApiOperation(value = "获取全部电影接口")
    @GetMapping("/")
    public Result getAllFilm() {
        return iFilmInfoService.getFilmList();
    }

    @ApiOperation(value = "搜索电影接口", notes = "匹配标题、导演、演员")
    @PostMapping("/{search}")
    public Result searchFilms(@PathVariable String search) {
        return iFilmInfoService.searchFilm(search);
    }

    @ApiOperation(value = "添加电影接口")
    @PostMapping("/")
    public Result addFilm(@RequestBody Filminfo filminfo) {
        return iFilmInfoService.addFilm(filminfo);
    }

    @ApiOperation(value = "修改电影信息接口")
    @PostMapping("/update")
    public Result updateFilmInfo(@RequestBody Filminfo filminfo) {
        return iFilmInfoService.updateFilmInfo(filminfo);
    }

    @ApiOperation(value = "根据ID删除电影接口")
    @DeleteMapping("/{id}")
    public Result deleteFilmById(@PathVariable int id) {
        return iFilmInfoService.deleteFilmById(id);
    }

    @ApiOperation(value = "批量删除电影接口", notes = "参数传json对象,其中包含ids(Array类型)")
    @DeleteMapping("/")
    public Result deleteFilms(@RequestBody Map<String, Object> params) {
        return iFilmInfoService.deleteFilms((List<Integer>)params.get("ids"));
    }
}
