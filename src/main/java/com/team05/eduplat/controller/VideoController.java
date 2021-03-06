package com.team05.eduplat.controller;

import com.team05.eduplat.entity.vo.Video.VideoVo;
import com.team05.eduplat.service.CourseService;
import com.team05.eduplat.service.Video.VideoService;
import com.team05.eduplat.utils.Result.ResultMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    CourseService courseService;
    @Value("${prop.upload-folder}")
    String uploadFolder;

    VideoVo vvo = new VideoVo();

    @ApiOperation("视频上传")
    @PostMapping("/upload")
    public void VideoUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value="guid")String guid,
                            @RequestParam(value="uid")Integer uid, @RequestParam(value="originName")String originName){
        vvo.uid = uid;
        vvo.name = originName;
        if (!file.isEmpty()) {
            try {
                String path = uploadFolder + "/" + guid;
                File tempDir = new File(path);//暂存文件夹
                if(!tempDir.exists()){
                    tempDir.mkdirs();
                }
                String fileName = file.getOriginalFilename();
                int splitIdx = fileName.lastIndexOf(".") == -1 ? fileName.length() : fileName.lastIndexOf(".");
                System.out.println(fileName.substring(0, splitIdx));
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(tempDir, fileName.substring(0, splitIdx) + ".part")));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation("视频合并")
    @GetMapping("/merge")
    public void VideoMerge(String guid) throws Exception {
        String path = uploadFolder + "/" + guid;
        File tempDir = new File(path);
        Date curTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        String time = simpleDateFormat.format(curTime);
        if (tempDir.isDirectory()) {
            String targetPath = uploadFolder + "/" + time;
            File targetDir = new File(targetPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            //开始合并
            File targetFile = new File(targetPath, guid + ".webm");
            FileChannel targetChannel = new FileOutputStream(targetFile, true).getChannel();
            int fileNums = tempDir.listFiles().length;
            for (int i = 0; i < fileNums; i++) {
                File partFile = new File(tempDir, guid + "_" + i + ".part");
                FileChannel partChannel = new FileInputStream(partFile).getChannel();
                targetChannel.transferFrom(partChannel, targetChannel.size(), partChannel.size());
                partChannel.close();
                partFile.delete();
            }
            tempDir.delete();
            targetChannel.close();

            //添加进数据库
            String url = targetPath + "/" + guid + ".webm";
            vvo.url = url;
//            vvo.setUid(0);
//            vvo.setName("你这是在测试吗");
            VideoAdd(vvo);
        }
    }

    @ApiOperation("视频获取")
    @GetMapping("get")
    public ResultMessage VideoGet(int userId) throws Exception{
       return videoService.findAll(userId);
    }

    @ApiOperation("视频修改/添加")
    @PostMapping("update")
    public  void VideoAdd(@RequestBody @Validated VideoVo vvo){
        System.out.println(vvo);
        videoService.add(vvo);
        return;
    }

}
