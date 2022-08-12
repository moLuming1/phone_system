package com.sdau.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.sdau.service.CourseService;
import com.sdau.service.StudentService;
import com.sdau.service.TeacherService;
import com.sdau.service.impl.TeacherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Scope("prototype")	// 作者要求每次读取都要使用新的Listener
public class ReadListener<T> extends AnalysisEventListener<T> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    private static int flag=1;

    private List<T> cachedDataList = new ArrayList<>();

    private TeacherService teacherService;
    private StudentService studentService;
    private CourseService courseService;

    public <E> ReadListener(E e) {
        String className = e.getClass().getName();
        System.out.println(className);
        if(className.contains("Teacher")){
            teacherService= (TeacherService) e;
        }else if(className.contains("Student")){
            studentService= (StudentService) e;
        }else{
            courseService= (CourseService) e;
        }

    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        //System.out.println(data);
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
           saveData();
            // 存储完成清理 list
            cachedDataList.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        if(flag==1){
            teacherService.saveBatchTeacher(cachedDataList);
        }
        log.info("存储数据库成功！");
    }


}
