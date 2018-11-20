package com.sec.aidog.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sec.aidog.dao.SysDeviceconfMapper;
import com.sec.aidog.pojo.SysDeviceconf;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("api")
@Controller
public class SysDeviceConfApi {
	
	@Autowired
	private SysDeviceconfMapper sysDeviceconfMapper;
	
	@Autowired
    private RedisService redisService;

	@ApiOperation(value = "通过项圈编号查询项圈配置信息", notes = "通过项圈编号查询项圈配置信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
        @ApiImplicitParam(name = "mid", value = "项圈标识", required = true, dataType = "String",paramType = "query")
	})
	@RequestMapping(value="getdeviceconfigbynecid",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> GetLayConfigByNeckletId(@RequestParam(value = "mid")String mid){
        JsonResult r = new JsonResult();
        try {
        	SysDeviceconf deviceconfig  = sysDeviceconfMapper.selectDeviceConfigByMid(mid);
            r.setCode(200);
            r.setMsg("获取项圈配置信息成功！");
            r.setData(deviceconfig);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取项圈配置信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
	
	
	
	@ApiOperation(value = "通过项圈编号配置项圈信息", notes = "通过项圈编号配置项圈信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
        @ApiImplicitParam(name = "mid", value = "项圈标识", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "status", value = "投药状态", required = true, dataType = "Byte",paramType = "query"),
        @ApiImplicitParam(name = "simccid", value = "SIM_CCID", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "swver", value = "版本号", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "ip", value = "ip地址", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "port", value = "端口号", required = true, dataType = "Integer",paramType = "query"),
        @ApiImplicitParam(name = "infoupdatecycle", value = "信息上传周期", required = true, dataType = "Integer",paramType = "query"),
        @ApiImplicitParam(name = "tickcycle", value = "心跳周期", required = true, dataType = "Integer",paramType = "query"),
        @ApiImplicitParam(name = "ledenable", value = "led使能", required = true, dataType = "Byte",paramType = "query"),
        @ApiImplicitParam(name = "tempflag", value = "临时投药标志", required = true, dataType = "Byte",paramType = "query"),
        @ApiImplicitParam(name = "tempgmt", value = "临时投药时间", required = true, dataType = "String",paramType = "query")
	})
	@RequestMapping(value="setdeviceconfigbynecid",method = RequestMethod.POST)
	@Transactional
    @ResponseBody
    public ResponseEntity<JsonResult> InsertLayConfigByNeckletId(@RequestParam(value = "mid")String mid,
    		@RequestParam(value = "status")Byte status,@RequestParam(value = "simccid")String simccid,@RequestParam(value = "swver")String swver,
    		@RequestParam(value = "ip")String ip,@RequestParam(value = "port")Integer port,
    		@RequestParam(value = "infoupdatecycle")Integer infoupdatecycle,@RequestParam(value = "tickcycle")Integer tickcycle,
    		@RequestParam(value = "ledenable")Byte ledenable,@RequestParam(value = "tempflag")Byte tempflag,
    		@RequestParam(value = "tempgmt")String tempgmt){
        JsonResult r = new JsonResult();
        try {
        	SysDeviceconf sysDeviceconf = sysDeviceconfMapper.selectDeviceConfigByMid(mid);
        	sysDeviceconf.setStatus(status);
        	sysDeviceconf.setSimccid(simccid);
        	sysDeviceconf.setSwver(swver);
        	sysDeviceconf.setIp(ip);
        	sysDeviceconf.setPort(port);
        	sysDeviceconf.setInfoupdatecycle(infoupdatecycle);
        	sysDeviceconf.setTickcycle(tickcycle);
        	sysDeviceconf.setLedenable(ledenable);
        	sysDeviceconf.setTemporaryflag(tempflag);
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
        	sysDeviceconf.setTemporarygmt(format.parse(tempgmt));
        	byte uimodifyflag = 1;
        	sysDeviceconf.setUimodifyflag(uimodifyflag);
        	byte hardmodifyflag = 0;
        	sysDeviceconf.setHardmodifyflag(hardmodifyflag);
        	sysDeviceconf.setUpdatetime(new Date());
        	
        	boolean flag  = sysDeviceconfMapper.updateByPrimaryKey(sysDeviceconf)==1?true:false;
        	boolean flag2  = false;
			boolean flag3 = false;
			if(flag) {
				String command03 = Analyse.Command_03_Send(sysDeviceconf);
				flag2  = redisService.set("device_"+mid, command03);
				flag3 = redisService.persistKey("device_"+mid);
			}
			if(flag2 && flag3) {
        		r.setCode(200);
                r.setMsg("配置项圈信息成功！");
                r.setData(null);
                r.setSuccess(true);
        	}else {
        		r.setCode(500);
                r.setData(null);
                r.setMsg("服务器忙，配置项圈信息失败");
                r.setSuccess(false);
        	}
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("配置项圈信息失败");
            r.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
