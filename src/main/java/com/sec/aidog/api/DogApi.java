package com.sec.aidog.api;

import com.sec.aidog.common.RedisUtil;
import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.NeckletMapper;
import com.sec.aidog.dao.PillMapper;
import com.sec.aidog.pojo.*;
import com.sec.aidog.service.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private NeckletMapper neckletMapper;

    @Autowired
    private PillMapper pillMapper;

    @Autowired
    private ManureService manureService;


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

            if (clicktype.equals("pilladd"))
            {
                String pillcode = json.getString("pillcode");
                String pillname = json.getString("pillname");
                String pillfactory = json.getString("pillfactory");
                String pillspec = json.getString("pillspec");
                String batchnum = json.getString("batchnum");
                String expdate = json.getString("expdate");
                String buydate = json.getString("buydate");
                String pillbuyer = json.getString("pillbuyer");
                String pillbuyertel = json.getString("pillbuyertel");
                String districtcode = json.getString("districtcode");
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
                    Pill pill = new Pill();
                    pill.setPillCode(pillcode);
                    pill.setPillName(pillname);
                    pill.setPillFactory(pillfactory);
                    pill.setPillSpec(pillspec);
                    pill.setPillBatchnum(batchnum);
                    pill.setPillExpdate(format.parse(expdate));
                    pill.setPillBuydate(format.parse(buydate));
                    pill.setPillBuyer(pillbuyer);
                    pill.setPillBuyertel(pillbuyertel);
                    pill.setDistrictcode(districtcode);
                    result = pillMapper.insert(pill)>0?"添加药珥成功！":"添加药珥失败!";
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "添加药珥失败!";
                }
            }

            else if (clicktype.equals("manureadd"))
            {
                String dogmanure_code = json.getString("dogmanure_code");
                Integer dogowner_id = json.getInt("dogowner_id");
                Integer dog_id = json.getInt("dog_id");
                String collection_person = json.getString("collection_person");
                String districtcode = json.getString("districtcode");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
                Date date = format.parse(json.getString("manure_getdate"));
                try {
                    result = manureService.addManure(dogmanure_code, dogowner_id, dog_id, collection_person,districtcode,date)==true?"采集犬粪成功!":"采集犬粪失败!";
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "采集犬粪失败!";
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

                Integer dogid = Integer.parseInt(json.getString("dogid"));
                String dogname = json.getString("dogname");
                String dogweight = json.getString("dogweight");
                String dogsex = json.getString("dogsex");
                String dogcolor = json.getString("dogcolor");
                Integer dogage = Integer.parseInt(json.getString("dogage"));
                try {
                    result = dogService.modifyDog(dogid, dogname, dogsex, dogweight, dogcolor, dogage);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "修改牧犬信息失败!";
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "operateapi",produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<JsonResult> OperateApi(@RequestBody JSONObject json, HttpServletRequest request) {
        String clicktype = json.getString("clicktype");
        String result = "";

        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            String managerstr = redisService.get("token:" + token);
            Manager manager = ((Manager) JSONUtil.JSONToObj(managerstr, Manager.class));
            //权限控制
            if (clicktype.equals("ownercheck"))
            {
                String ownername = json.getString("ownername");
                String owneridentity = json.getString("owneridentity");
                String ownerhamletcode = json.getString("ownerhamletcode");
                String telphone = json.getString("telphone");

                try {
                    Map<String,Object> map = ownerService.checkOwner(ownername, owneridentity, ownerhamletcode, telphone);
                    r.setCode(200);
                    r.setMsg("获取村主人信息成功！");
                    r.setData(map);
                    r.setSuccess(true);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    r.setCode(500);
                    r.setData(e.getClass().getName() + ":" + e.getMessage());
                    r.setMsg("获取村主人信息失败");
                    r.setSuccess(false);
                    e.printStackTrace();
                }
            }else if (clicktype.equals("owneradd"))
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
                    Dogowner owner = ownerService.addOwner(ownername, owneridentity, ownersex, ownerhamletcode, ownerage, ownerjob, homeaddress, telphone);
                    if(owner!=null){
                        r.setCode(200);
                        r.setMsg("添加成功或已存在该主人!");
                        r.setData(owner);
                        r.setSuccess(true);
                    }else{
                        r.setCode(500);
                        r.setMsg("主人添加失败!");
                        r.setSuccess(false);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    r.setCode(500);
                    r.setData(e.getClass().getName() + ":" + e.getMessage());
                    r.setMsg("添加主人失败!");
                    r.setSuccess(false);
                    e.printStackTrace();
                }
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
                    Dog dog = dogService.addDog(username, dogname, dogsex, dogbelonghamlet, ownerhamletcode, dogownerid, dogweight, dogcolor, dogage,govcode);
                    if(dog!=null){
                        r.setCode(200);
                        r.setMsg("添加成功或已存在该犬只!");
                        r.setData(dog);
                        r.setSuccess(true);
                    }else{
                        r.setCode(500);
                        r.setMsg("犬只添加失败!");
                        r.setSuccess(false);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    r.setCode(500);
                    r.setData(e.getClass().getName() + ":" + e.getMessage());
                    r.setMsg("添加犬只失败!");
                    r.setSuccess(false);
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("处理信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
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
            @ApiImplicitParam(name = "startitem", value = "startitem", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pagesize", value = "pagesize", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "level", value = "地区等级", required = true , dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "districtcode", value = "行政编码", required = true , dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="getdoglist",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> GetDogList(@RequestParam(value = "startitem") int startitem,@RequestParam(value = "pagesize") int pagesize,@RequestParam(value = "level",required = true)String level,@RequestParam(value = "districtcode",required = true)String districtcode, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制

            switch (level){
                case "province":
                    districtcode = districtcode.substring(0,2);
                    break;
                case "city":
                    districtcode = districtcode.substring(0,4);
                    break;
                case "county":
                    districtcode = districtcode.substring(0,6);
                    break;
                case "village":
                    districtcode = districtcode.substring(0,9);
                    break;
            }
            Map<String, Object> map = dogService.getDogList(districtcode,startitem,pagesize);
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

    //获取牧犬详细信息
    @ApiOperation(value = "获取牧犬详细信息", notes = "获取牧犬详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dogid", value = "dogid", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="getdoginfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> GetDogInfo(@RequestParam(value = "dogid") int dogid, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制
            Map<String, Object> map = dogService.getDogInfo(dogid);
            r.setCode(200);
            r.setMsg("获取牧犬信息成功！");
            r.setData(map);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取牧犬信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "获取未绑定牧犬及项圈列表", notes = "获取未绑定牧犬及项圈列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "hamletcode", value = "村行政编码", required = true , dataType = "String",paramType = "query")
    })
    @RequestMapping(value="getunbinddogandnec",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> Getunbinddogandnec(@RequestParam(value = "hamletcode",required = true)String hamletcode, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制

            Map<String, Object> map = new HashMap<>();
            List<DogView> unusedoggovcodelist = dogMapper.getUnuseDogGovcodeList(hamletcode);
            map.put("govcodelist",unusedoggovcodelist);
            List<String> unuseneclist = neckletMapper.getUnuseNecList();
            map.put("neclist",unuseneclist);
            r.setCode(200);
            r.setMsg("获取列表信息成功！");
            r.setData(map);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取列表信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

}
