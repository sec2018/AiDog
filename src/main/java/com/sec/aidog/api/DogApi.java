package com.sec.aidog.api;

import com.sec.aidog.common.RedisUtil;
import com.sec.aidog.pojo.Manager;
import com.sec.aidog.service.DogService;
import com.sec.aidog.service.OwnerService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.JSONUtil;
import com.sec.aidog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("api")
@Controller
public class DogApi {

    @Autowired
    private DogService dogService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OwnerService ownerService;



    @RequestMapping(value = "bindoraddapi",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String BindOrAddApi(@RequestBody JSONObject json, HttpServletRequest request) {
        String clicktype = json.getString("clicktype");
        String result = "";

        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            String managerstr = redisService.get("token:" + token);
            Manager manager = ((Manager) JSONUtil.JSONToObj(managerstr, Manager.class));
            //权限控制

            if (clicktype.equals("owneradd"))
            {
                String ownername = json.getString("ownername");
                String owneridentity = json.getString("owneridentity");
                String ownersex = json.getString("ownersex");
                String ownerhamlet = json.getString("ownerhamlet");
                String ownerhamletcode = json.getString("ownerhamletcode");
                int ownerage = json.getInt("ownerage");
                String ownerjob = json.getString("ownerjob");
                String homeaddress = json.getString("homeaddress");
                String telphone = json.getString("telphone");

                try {
                    result = ownerService.addOwner(ownername, owneridentity, ownersex, ownerhamletcode, ownerage, ownerjob, homeaddress, telphone);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "添加主人失败!";
                }
            }

            else if (clicktype.equals("neckletadd"))
            {
//                String neckletid = json.getString("neckletid");
//                int medtotal = json.getInt("medtotal");
//                String category = json.getString("category");
//                String username = json.getString("username");
//                try {
//                    result = manageService.addNecklet(neckletid, medtotal, category, username);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "添加项圈失败!";
//                }
            }

            else if (clicktype.equals("feederadd"))
            {

//                String apparatusid = json.getString("feederid");
//                int medtotal = json.getInt("medtotal");
//                String category = json.getString("category");
//                String username = json.getString("username");
//                try {
//                    result = manageService.addFeeder(apparatusid, medtotal, category, username);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "添加喂食器失败!";
//                }

            }else if(clicktype.equals("bindfeeder")) {

//                String username = json.getString("username");
//                String dogname = json.getString("dogname");
//                String dogsex = json.getString("dogsex");
//                String dogbelonghamlet = json.getString("dogbelonghamlet");
//                String ownerhamletcode = json.getString("ownerhamletcode");
//                String dogownerid = json.getString("dogownerid");
//                String dogweight = json.getString("dogweight");
//                String dogcolor = json.getString("dogcolor");
//                String dogage = json.getString("dogage");
//                String dogfeederid = json.getString("dogfeederid");
//                try {
//                    result = manageService.bindFeeder(username, dogname, dogsex, dogbelonghamlet, ownerhamletcode, dogownerid, dogweight, dogcolor, dogage, dogfeederid);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "绑定喂食器失败!";
//                }
            }else if(clicktype.equals("bindnecklet")) {

//                String username = json.getString("username");
//                String dogname = json.getString("dogname");
//                String dogsex = json.getString("dogsex");
//                String dogbelonghamlet = json.getString("dogbelonghamlet");
//                String ownerhamletcode = json.getString("ownerhamletcode");
//                String dogownerid = json.getString("dogownerid");
//                String dogweight = json.getString("dogweight");
//                String dogcolor = json.getString("dogcolor");
//                String dogage = json.getString("dogage");
//                String dogneckletid = json.getString("dogneckletid");
//                try {
//                    result = manageService.bindNecklet(username, dogname, dogsex, dogbelonghamlet, ownerhamletcode, dogownerid, dogweight, dogcolor, dogage, dogneckletid);
//                } catch (Exception e) {
//                    logger.error("【系统错误】",e);
//                    result = "绑定项圈失败!";
//                }
            }else if(clicktype.equals("dogadd")) {

                String username = json.getString("username");
                String dogname = json.getString("dogname");
                String dogsex = json.getString("dogsex");
                String dogbelonghamlet = json.getString("dogbelonghamlet");
                String ownerhamletcode = json.getString("ownerhamletcode");
                String dogownerid = json.getString("dogownerid");
                String dogweight = json.getString("dogweight");
                String dogcolor = json.getString("dogcolor");
                int dogage = Integer.parseInt(json.getString("dogage"));
                String govcode = json.getString("govcode");
                try {
                    result = dogService.addDog(username, dogname, dogsex, dogbelonghamlet, ownerhamletcode, dogownerid, dogweight, dogcolor, dogage,govcode);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "添加牧犬失败!";
                }
            }else if(clicktype.equals("neckletmodify")) {

//                String neckletid = json.getString("neckletid");
//                String power = json.getString("power");
//                String medtotal = json.getString("medtotal");
//                String medleft = json.getString("medleft");
//                String areacycle = json.getString("areacycle");
//                String exhibitcycle = json.getString("exhibitcycle");
//                String firstmedtime = json.getString("firstmedtime");
//
//                try {
//                    result = manageService.modifyNecklet(neckletid, power, medtotal, medleft, areacycle, exhibitcycle, firstmedtime);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "修好项圈信息失败!";
//                }
            }else if(clicktype.equals("feedermodify")) {

//                String feederid = json.getString("feederid");
//                String power = json.getString("power");
//                String medtotal = json.getString("medtotal");
//                String medleft = json.getString("medleft");
//                String areacycle = json.getString("areacycle");
//                String exhibitcycle = json.getString("exhibitcycle");
//                String firstmedtime = json.getString("firstmedtime");
//                try {
//                    result = manageService.modifyFeeder(feederid, power, medtotal, medleft, areacycle, exhibitcycle, firstmedtime);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "修改喂食器信息失败!";
//                }
            }else if(clicktype.equals("ownermodify")) {

//                String ownerid = json.getString("ownerid");
//                String ownername = json.getString("ownername");
//                String owneridentity = json.getString("owneridentity");
//                String ownersex = json.getString("ownersex");
//                String ownerage = json.getString("ownerage");
//                String ownerjob = json.getString("ownerjob");
//                String homeaddress = json.getString("homeaddress");
//                String telphone = json.getString("telphone");
//
//                try {
//                    result = manageService.modifyOwner(ownerid, ownername, owneridentity, ownersex, ownerage, ownerjob, homeaddress, telphone);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "修改主人信息失败!";
//                }
            }else if(clicktype.equals("dogmodify")) {

//                String username = json.getString("username");
//                String dogid = json.getString("dogid");
//                String dogname = json.getString("dogname");
//                String dogsex = json.getString("dogsex");
//                String dogbelonghamlet = json.getString("dogbelonghamlet");
//                String districtcode = json.getString("districtcode");
//                String dogownerid = json.getString("dogownerid");
//                String dogweight = json.getString("dogweight");
//                String dogcolor = json.getString("dogcolor");
//                String dogage = json.getString("dogage");
//                String dogneckletid = json.getString("dogneckletid");
//                String dogfeederid = json.getString("dogfeederid");
//                try {
//                    result = manageService.modifyDog(username, dogid, dogname, dogsex, dogbelonghamlet, districtcode, dogownerid, dogweight, dogcolor, dogage, dogneckletid, dogfeederid);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    logger.error("【系统错误】",e);
//                    result = "修改牧犬信息失败!";
//                }
            }
        }catch (Exception e) {

        }
        return result.toString();
    }


    @ApiOperation(value = "获取某村主人列表", notes = "获取某村主人列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "hamletcode", value = "村行政编码", required = true , dataType = "String",paramType = "query")
    })
    @RequestMapping(value="gethamletownerlist",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> GetHamletOwnerList(@RequestParam(value = "hamletcode",required = true)String hamletcode, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制

            Map<String, Object> map = ownerService.getHamletOwnersList(hamletcode);
            r.setCode(200);
            r.setMsg("获取村主人列表信息成功！");
            r.setData(map);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取村主人列表信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    //获取牧犬列表，根据地区编号
    @ApiOperation(value = "获取牧犬列表", notes = "获取牧犬列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "districtcode", value = "行政编码", required = true , dataType = "String",paramType = "query")
    })
    @RequestMapping(value="getdoglist",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> GetDogList(@RequestParam(value = "districtcode",required = true)String districtcode, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制

            Map<String, Object> map = dogService.getDogList(districtcode);
            r.setCode(200);
            r.setMsg("获取牧犬列表信息成功！");
            r.setData(map);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取牧犬列表信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
