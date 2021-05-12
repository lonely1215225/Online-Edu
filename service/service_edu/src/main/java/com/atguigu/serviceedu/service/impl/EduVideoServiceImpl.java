package com.atguigu.serviceedu.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.exceptionhandler.GuliException;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.client.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
@Transactional
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void removeByCourseId(String courseId) {
        //根据课程id查出所有视频的id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);
        //封装video_source_id  1,2,3
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!videoSourceId.isEmpty()) {
                list.add(videoSourceId);
            }
        }
        //list不为空
        if (list.size() > 0) {
            //删除小节里的所有视频
            R result = vodClient.deleteBatch(list);
            if (result.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，熔断器...");
            }
        }
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void updateSpecialColumn(EduVideo eduVideo) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        if (Objects.nonNull(eduVideo.getTitle())){
            updateWrapper.set("title",eduVideo.getTitle());
        }
        if (Objects.nonNull(eduVideo.getSort())){
            updateWrapper.set("sort",eduVideo.getSort());
        }
        if (Objects.nonNull(eduVideo.getIsFree())){
            updateWrapper.set("is_free",eduVideo.getIsFree());
        }
        if (Objects.nonNull(eduVideo.getVideoSourceId())){
            updateWrapper.set("video_source_id",eduVideo.getVideoSourceId());
        }
        updateWrapper.set("gmt_modified",new Date());
        updateWrapper.eq("id",eduVideo.getId());
        updateWrapper.set("version",eduVideo.getVersion()+1);
        baseMapper.update(eduVideo,updateWrapper);
    }


}
