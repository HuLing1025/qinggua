package com.yznu.qinggua.controller.desktop;

import com.yznu.qinggua.service.IFilmInfoService;
import com.yznu.qinggua.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "客户端电影操作接口")
@RestController
@RequestMapping("/desktop/film")
public class FilmInfoController {
    @Autowired
    IFilmInfoService iFilmInfoService;

    @ApiOperation(value = "搜索电影", notes = "匹配标题、导演、演员")
    @PostMapping("/{search}")
    public Result searchFilms(@PathVariable String search) {
        return iFilmInfoService.searchFilm(search);
    }

}
