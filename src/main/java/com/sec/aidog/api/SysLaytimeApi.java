package com.sec.aidog.api;


import com.sec.aidog.dao.SysLaytimeMapper;
import com.sec.aidog.mgpojo.MgSysLaytime;
import com.sec.aidog.model.SysLaytimeExample;
import com.sec.aidog.mongoservice.MgLaytimeService;
import com.sec.aidog.pojo.SysLaytime;
import com.sec.aidog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RequestMapping("api")
@Controller
public class SysLaytimeApi {
	
	@Autowired
	private SysLaytimeMapper sysLaytimeMapper;

	@Autowired
    private MgLaytimeService mgLaytimeService;

	@ApiOperation(value = "通过项圈编号查询项圈反馈信息", notes = "通过项圈编号查询项圈反馈信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "mid", value = "项圈标识", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "startitem", value = "第几页", required = true, dataType = "Integer",paramType = "query"),
        @ApiImplicitParam(name = "pagesize", value = "每页条数", required = true, dataType = "Integer",paramType = "query")
	})
	@RequestMapping(value="getlaytimebynecid",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> GetLayTimeByNeckletId(@RequestParam(value = "mid")String mid,
                                                            @RequestParam(value = "startitem")Integer startitem,
                                                            @RequestParam(value = "pagesize")Integer pagesize)
    {
        JsonResult r = new JsonResult();
        try {
            ////通过MySQL
//        	SysLaytimeExample example = new SysLaytimeExample();
//        	SysLaytimeExample.Criteria criteria = example.createCriteria();
//        	criteria.andMidEqualTo(mid);
//        	List<SysLaytime> layconfiglist  = sysLaytimeMapper.selectByExample(example);
            ////通过mongodb
            List<MgSysLaytime> laytimelist = mgLaytimeService.getMidLaytimeList(mid, startitem, pagesize);
            r.setCode(200);
            r.setMsg("获取项圈反馈成功！");
            r.setData(laytimelist);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取项圈反馈失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "获取所有项圈当前位置", notes = "获取所有项圈当前位置")
    @RequestMapping(value="getdeviceposition",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> GetDevicePosition(){
        JsonResult r = new JsonResult();
        try {
//            List<SysLaytime> allDevicePosition  = sysLaytimeMapper.getAllDevicePosition();
            List<MgSysLaytime> allDevicePosition = mgLaytimeService.getAllDevicePosition();
            r.setCode(200);
            r.setMsg("获取所有项圈位置成功！");
            r.setData(allDevicePosition);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取所有项圈位置失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
