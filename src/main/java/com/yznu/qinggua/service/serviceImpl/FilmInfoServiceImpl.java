package com.yznu.qinggua.service.serviceImpl;

import com.yznu.qinggua.dao.IFilmInfoDao;
import com.yznu.qinggua.pojo.Filminfo;
import com.yznu.qinggua.service.IFilmInfoService;
import com.yznu.qinggua.utils.ResponseUtil;
import com.yznu.qinggua.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilmInfoServiceImpl implements IFilmInfoService {
    @Autowired
    IFilmInfoDao iFilmInfoDao;

    @Override
    public Result getFilmList() {
        try{
            List<Map<String, Object>> films;
            // 查询所有电影
            films = iFilmInfoDao.selectAllFilm();
            if(films.size() == 0){
                return ResponseUtil.error(400, "电影列表为空");
            }
            return ResponseUtil.success(films, 200, "成功获取电影列表!");
        }catch (Exception e){
            return ResponseUtil.error(500, "获取电影列表失败,异常: " + e);
        }
    }

    @Override
    public Result searchFilm(String search) {
        try{
            List<Map<String, Object>> films;
            // 搜索
            films = iFilmInfoDao.searchFilm(search);
            if (films.size() == 0){
                return ResponseUtil.error(400, "没有找到相关电影!");
            }
            return ResponseUtil.success(films, 200, "搜索结果获取成功!");
        }catch (Exception e){
            return ResponseUtil.success(500, "搜索结果获取失败,异常: " + e);
        }
    }

    @Override
    public Result getFilmById(int id) {
        try{
            Filminfo filminfo;
            // 查询
            filminfo = iFilmInfoDao.selectFilmById(id);
            if (filminfo == null){
                return ResponseUtil.error(400, "没有找到该电影!");
            }
            return ResponseUtil.success(filminfo, 200, "获取电影信息成功!");
        }catch (Exception e){
            return ResponseUtil.error(500, "获取电影信息失败,异常: " + e);
        }
    }

    @Override
    public Result addFilm(Filminfo filminfo) {
        try{
            // 添加
            if (iFilmInfoDao.insertOne(filminfo) == 1){
                return ResponseUtil.success(200, "添加电影成功!");
            }
            return ResponseUtil.error(400, "添加电影失败!");
        }catch (Exception e){
            return ResponseUtil.error(500, "添加电影失败,异常: " + e);
        }
    }

    @Override
    public Result updateFilmInfo(Filminfo filminfo) {
        try{
            // 修改
            if(iFilmInfoDao.updateFilm(filminfo) == 1){
                return ResponseUtil.success(200, "成功修改电影信息!");
            }
            return ResponseUtil.error(400, "修改电影信息失败!");
        }catch (Exception e){
            return ResponseUtil.error(500, "修改电影信息失败,异常: " + e);
        }
    }

    @Override
    public Result deleteFilmById(int id) {
        try{
            // 删除
            if (iFilmInfoDao.deleteFilmById(id) == 1){
                return ResponseUtil.success(200, "删除电影成功!");
            }
            return ResponseUtil.error(400, "删除电影失败!");
        }catch (Exception e){
            return ResponseUtil.error(500, "删除电影失败,异常: " + e);
        }
    }

    @Override
    public Result deleteFilms(List<Integer> ids) {
        try{
            // 批量删除
            if(iFilmInfoDao.deleteFilms(ids) == ids.size()){
                return ResponseUtil.success(200, "批量删除电影成功!");
            }
            return ResponseUtil.error(400, "批量删除电影失败!");
        }catch (Exception e){
            return ResponseUtil.error(500, "批量删除电影失败,异常: " + e);
        }
    }
}
