package com.sec.aidog.api;

import com.sec.aidog.common.DistrictCommon;
import com.sec.aidog.common.RedisUtil;
import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.DogownerMapper;
import com.sec.aidog.dao.NeckletMapper;
import com.sec.aidog.dao.PillMapper;
import com.sec.aidog.pojo.*;
import com.sec.aidog.service.*;
import com.sec.aidog.util.JSONUtil;
import com.sec.aidog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
    private DogownerMapper dogownerMapper;

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private NeckletMapper neckletMapper;

    @Autowired
    private PillMapper pillMapper;

    @Autowired
    private ManureService manureService;

    @Autowired
    private UserService userService;

    @Autowired
    private DistrictCommon districtCommon;


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
                    result = pillMapper.insert(pill)>0?"添加药饵成功！":"添加药饵失败!";
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "添加药饵失败!";
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
            else if (clicktype.equals("manuremodify"))
            {
                Integer manureid = json.getInt("manureid");
                String testmethod = json.getString("testmethod");
                String testresult = json.getString("testresult");
                String testperson = json.getString("testperson");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
                Date testdate = format.parse(json.getString("testdate"));
                try {
                    result = manureService.modifyManure(manureid, testmethod, testdate, testresult,testperson)==true?"录入犬粪检测结果成功!":"录入犬粪检测结果失败!";
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    result = "录入犬粪检测结果失败!";
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
                    if(map == null){
                        r.setCode(500);
                        r.setMsg("获取村主人信息失败");
                        r.setData(map);
                        r.setSuccess(false);
                    }else{
                        r.setCode(200);
                        r.setMsg("获取村主人信息成功！");
                        r.setData(map);
                        r.setSuccess(true);
                    }
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
                int dogownerid = Integer.parseInt(json.getString("dogownerid"));
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
            }else if(clicktype.equals("ownermodify")) {
                String ownerid = json.getString("ownerid");
                String ownername  = json.getString("ownername");
                String owneridentity = json.getString("owneridentity");
                String ownersex = json.getString("ownersex");
                String ownerage = json.getString("ownerage");
                String ownerjob = json.getString("ownerjob");
                String owneraddr = json.getString("owneraddr");
                String ownertel = json.getString("ownertel");
                try {
                    Dogowner dogowner = dogownerMapper.selectByPrimaryKey(Integer.parseInt(ownerid));
                    dogowner.setOwnerName(ownername);
                    dogowner.setOwnerIdentity(owneridentity);
                    dogowner.setOwnerSex(ownersex);
                    dogowner.setOwnerAge(Integer.parseInt(ownerage));
                    dogowner.setOwnerJob(ownerjob);
                    dogowner.setOwnerAddr(owneraddr);
                    dogowner.setOwnerTel(ownertel);
                    boolean flag = dogownerMapper.updateByPrimaryKey(dogowner) == 1?true:false;
                    if(flag){
                        r.setCode(200);
                        r.setMsg("修改犬主成功!");
                        r.setData(dogowner);
                        r.setSuccess(true);
                    }else{
                        r.setCode(500);
                        r.setMsg("修改犬主失败!");
                        r.setSuccess(false);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    r.setCode(500);
                    r.setData(e.getClass().getName() + ":" + e.getMessage());
                    r.setMsg("修改犬主失败!");
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
    @ApiOperation(value = "获取犬只详细信息", notes = "获取犬只详细信息")
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
            r.setMsg("获取犬只信息成功！");
            r.setData(map);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取犬只信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }


    @ApiOperation(value = "获取未绑定犬只及项圈列表", notes = "获取未绑定犬只及项圈列表")
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


    // excel导入
    @ApiOperation(value = "从excel中批量注册犬只及犬主人", notes = "从excel中批量注册犬只及犬主人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="batchdogregexcel",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> export(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = file.getInputStream();
//            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);  //仅支持07之前的xls
            Workbook wb = WorkbookFactory.create(inputStream);
//            XSSFWorkbook wb = new XSSFWorkbook(poifsFileSystem);  //仅支持07之前的xls
            // 2.读取页脚sheet
            //HSSFSheet sheetAt = wb.getSheetAt(0);  //仅支持07之前的xls
            Sheet sheetAt = wb.getSheetAt(0);
            // 3.循环读取某一行
            int rownum = 0;
            String res = "";
            for (Row row : sheetAt) {
                if(rownum == 0){
                    rownum++;
                    continue;
                }
                // 4.读取每一行的单元格
                String province = row.getCell(0).getStringCellValue(); // 第一列数据
                String city = row.getCell(1).getStringCellValue();// 第二列
                String county = row.getCell(2).getStringCellValue();//
                String village = row.getCell(3).getStringCellValue();//
                String hamlet = row.getCell(4).getStringCellValue();//
                String ownername = row.getCell(5).getStringCellValue();//
                String owneridentity = row.getCell(6).getStringCellValue();//
                String ownertel = row.getCell(7).getStringCellValue();//
                String dogname = row.getCell(8).getStringCellValue();//
                String managername = row.getCell(9).getStringCellValue();//
                String dogcovcode = row.getCell(10).getStringCellValue();//
                String ownersex = row.getCell(11).getStringCellValue();//
                int ownerage = Integer.parseInt((row.getCell(12).getNumericCellValue()+"").split("\\.")[0]);
                String ownerjob = row.getCell(13).getStringCellValue();//
                String owneraddr = row.getCell(14).getStringCellValue();//
                String dogsex = row.getCell(15).getStringCellValue();//
                String dogweight = row.getCell(16).getStringCellValue();//
                String dogcolor = row.getCell(17).getStringCellValue();//
                int dogage =  Integer.parseInt((row.getCell(18).getNumericCellValue()+"").split("\\.")[0]);
                // 写多少个具体看大家上传的文件有多少列.....
                // 测试是否读取到数据,及数据的正确性
                res += province+"   "+city+"   "+county+"   "+village+"   "+hamlet+"   "+ownername+"   "+owneridentity+"   "+ownertel+"   "+
                        dogname+"   "+managername+"   "+dogcovcode+"   "+ownersex+"   "+ownerage+"   "+ownerjob+"   "+owneraddr+"   "+dogsex+"   "+dogweight+"   "+
                        dogcolor+"   "+dogage+"<\\br>";

                //注册逻辑
                District thishamlet = districtCommon.GetDistrictcode(province,city,county,village,hamlet);
                String hamletcode = thishamlet.getDistrictcode();
                //主人注册
                Dogowner owner = ownerService.addOwner(ownername, owneridentity, ownersex, hamletcode, ownerage, ownerjob, owneraddr, ownertel);
                if(owner==null){
                    continue;
                }
                //犬只注册
                String username = userService.selectByNameAndDistrictcode(managername,hamletcode).getUsername();
                Dog dog = dogService.addDog(username, dogname, dogsex, hamlet, hamletcode, owner.getOwnerId(), dogweight, dogcolor, dogage,dogcovcode);
                if(dog == null){
                    continue;
                }
            }
            r.setCode(200);
            r.setMsg("批量插入信息成功！");
            r.setData(res);
            r.setSuccess(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            r.setCode(500);
            r.setMsg("批量插入信息失败！");
            r.setData(null);
            r.setSuccess(false);
        }
        return ResponseEntity.ok(r);
    }

    //获取犬只详细信息
    @ApiOperation(value = "获取犬主详细信息", notes = "获取犬主详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ownerid", value = "dogid", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="getownerinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonResult> GetOwnerInfo(@RequestParam(value = "ownerid") int ownerid, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            //权限控制
            Dogowner owner = dogownerMapper.selectByPrimaryKey(ownerid);
            r.setCode(200);
            r.setMsg("获取主人信息成功！");
            r.setData(owner);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取主人信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
