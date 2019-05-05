package com.example.springbootdemo.io;

import com.alibaba.fastjson.JSON;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ExcelUtil
 *
 * @author zhengzhongpeng
 * @date 2019/4/30 9:05
 */
public class ExcelUtil {

    private static final String preStr = "INSERT INTO `zhubajie_bj_log`.`bjl_log` (`manager_id`, `manager_name`, `sys_id`,`business_id`, `related_key`, `detail`, `create_time`) VALUES (";

    private static final String endWith = ");";

    private static final String dit = "'";

    public static void main(String[] args) throws IOException, InterruptedException {
        //需要 解析的 数据源文件
        InputStream inputStream = new FileInputStream(new File("F:/log/source/2019-03-01以后.csv"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        //生成 新的文件名
        String tagFileName = "F:/log/target/2019-03-01以后-";
        Long fileNum = 1L;
        String fileNameEndWith = ".txt";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tagFileName+fileNum+fileNameEndWith)));
        CSVReader csvReader = new CSVReaderBuilder(bf).build();
        Iterator<String[]> iterator = csvReader.iterator();
        iterator.next();//去除第一行
        Long num = 0L;  //读取的条数（除第一行）
        while (iterator.hasNext()){
            String[] lineContent = iterator.next();
            String managerId = stringValue(2,lineContent);
            String managerName = stringValue(3,lineContent);
            String create_time = stringValue(6,lineContent);
            String detail = stringValue(7,lineContent);
            String relatedKey = stringValue(9,lineContent) != null ?stringValue(9,lineContent):"";
            String isLog = stringValue(12,lineContent) != null? stringValue(12,lineContent):"0";//'0:备注,1:日志',
            String type = stringValue(1,lineContent); //28 - 详情页   33-管家
            int bussiness_id = 0;
            try {
                bussiness_id = Integer.valueOf(type) == 28 ? 0 : Integer.valueOf(isLog) == 1 ?  8 : 9;
            }catch (Exception e){
                bussiness_id = 222;//无效数据
            }
            //构建sql
            StringBuilder sb = new StringBuilder();
            sb.append(preStr).append(dit).append(managerId).append(dit).append(",")//managerId
                    .append(dit).append(managerName).append(dit).append(",")//managerName;
                    .append(dit).append(2).append(dit).append(",")//系统ID sys_id
                    .append(dit).append(bussiness_id).append(dit).append(",")//business_id  管家日志8  备注 9   其他详情页 0
                    .append(dit).append(relatedKey).append(dit).append(",")//related_key
                    .append(dit).append(detail).append(dit).append(",")//detail
                    .append(dit).append(dateToStr(create_time)).append(dit)//create_time
                    .append(endWith);//
            writer.write(sb.toString());
            writer.newLine();
            num++;
            if (num.equals(150000*fileNum)){
                System.out.println("当前条数"+num);
                System.out.println("第"+fileNum+"个文件写入完成");
                fileNum++;
                Thread.sleep(500);
                writer.flush();
                writer.close();
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tagFileName+fileNum+fileNameEndWith)));
                Thread.sleep(1000);

            }
//            if (num>=100){
//                System.out.println("当前条数"+num);
//                return;
//            }
        }
        System.out.println(num);
        writer.flush();
        writer.close();
        bf.close();
        System.out.println("程序执行完成");
    }

    private static String stringValue(Integer index,String[] list){
        try {
            String value = list[index];
            return value;
        }catch (Exception e){
            return null;
        }
    }

    private static String dateToStr(String str){
        try {
            Date date = new Date(Long.valueOf(str)*1000);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(date);
        }catch (Exception e){
            return "0000-00-00 00:00:00";
        }
    }


    public static void main1(String [] strings) throws IOException{
        MultipartFile  multipartFile = getMultipartFile("F:/log/source/1244.xlsx");
//        MultipartFile  multipartFile = getMultipartFile("F:/log/source/124.xlsx");
        Workbook workbook = getWorkBook(multipartFile);
        Sheet sheet = workbook.getSheetAt(0);//获取第一张工作表
        Integer firstRow = sheet.getFirstRowNum();
        Integer lastRow = sheet.getLastRowNum();
        for (int rowNum = firstRow;rowNum <= lastRow;rowNum++){
            //获取当前行
            Row row = sheet.getRow(rowNum);
            if (row == null){
                continue;
            }
            int c = row.getRowNum();
            int firstCell = row.getFirstCellNum();
            int lastCell = row.getLastCellNum();
            List<String> lineList = new ArrayList<>();
            for (int i = firstCell;i<= lastCell;i++){
                Cell cell = row.getCell(i);
                lineList.add(getCellValue(cell));
            }
            System.out.println(lineList.toString());
        }
        System.out.println();


    }

    //获取MultipartFile
    public static MultipartFile getMultipartFile(String path) throws IOException {
        File file = new File(path);
        return new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),new FileInputStream(file));
    }
    //获取工作簿
    public static Workbook getWorkBook(MultipartFile file){
        //文件名
        String fileName = file.getOriginalFilename();
        //获取工作簿对象
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith("xls")){
                workbook = new HSSFWorkbook(is);
            }else if (fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}
