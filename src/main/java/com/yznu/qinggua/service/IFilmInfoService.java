package com.yznu.qinggua.service;

import com.yznu.qinggua.pojo.Filminfo;
import com.yznu.qinggua.utils.Result;

import java.util.List;

public interface IFilmInfoService {
    /**
     * 查询全部电影
     * @return
     * */
    Result getFilmList();

    /**
     * 搜索电影
     * @param search
     * @return
     * */
    Result searchFilm(String search);

    /**
     * 根据ID查询电影
     * @param id
     * @return
     * */
    Result getFilmById(int id);

    /**
     * 新增电影
     * @param filminfo
     * @return
     * */
    Result addFilm(Filminfo filminfo);

    /**
     * 修改电影信息
     * @param filminfo
     * @return
     * */
    Result updateFilmInfo(Filminfo filminfo);

    /**
     * 根据ID删除电影
     * @param id
     * @return
     * */
    Result deleteFilmById(int id);

    /**
     * 批量删除电影
     * @param ids
     * @return
     * */
    Result deleteFilms(List<Integer> ids);
}
