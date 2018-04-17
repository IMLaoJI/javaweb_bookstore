package com.laoji.bookstore.web.servlet;

import com.google.gson.JsonObject;
import com.laoji.bookstore.domain.FileMeta;
import com.laoji.bookstore.domain.Result;
import com.laoji.bookstore.utils.JsonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LONG on 2017/11/23.
 */
public class UploadServlet extends BaseServlet {

    JsonObject resultJson=new JsonObject();
    JsonObject resultJson1=new JsonObject();

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //解决post请求的乱码
        //request.setCharacterEncoding("utf-8");

	/*	//获取一个输入流
		ServletInputStream in = request.getInputStream();

		//读取流
		String str = IOUtils.toString(in);

		System.out.println(str);*/

        Result result =new Result();
        //获取工厂类实例
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //创建解析器类实例
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        LinkedList<FileMeta> files = new LinkedList<FileMeta>();
        String contentType ;
        String name;
        long size;
        FileMeta fileMeta = new FileMeta();
        //fileUpload通过该对象来限制文件的大小
        //设置当文件的大小为50KB
        fileUpload.setFileSizeMax(50*1024);

        //设置多个文件的总大小为150kb
        //fileUpload.setSizeMax(1024*150);

        try {
            //解析request
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            //遍历fileItems，读取表单的信息
            for (FileItem fileItem : fileItems) {

                //判断当前表单项是否是一个普通表单项
                if(fileItem.isFormField()){
                    //获取属性名
                    String fieldName = fileItem.getFieldName();

                    //获取属性值
                    String value = fileItem.getString("utf-8");

                    System.out.println(fieldName+" = "+value);
                }else{
                    //如果是文件表单项
                    //获取文件的大小
                     size = fileItem.getSize();

                    //判断size是否为0
                    if(size==0){
                        continue;
                    }

                    //获取文件的类型
                     contentType = fileItem.getContentType();

                    //获取文件的名字
                     name = fileItem.getName();
                    //判断name中是否包含有路径信息
                    if(name.contains("\\")){
                        //如果包含则截取字符串
                        name = name.substring(name.lastIndexOf("\\")+1);
                    }

                    //生成一个UUID，作为文件名的前缀
                    String prefix = UUID.randomUUID().toString().replace("-", "");
                    name = prefix+"_"+name;

                    fileMeta = new FileMeta();
                    //获取表单项的属性名
                    String fieldName = fileItem.getFieldName();
                    if (!"application/kset".equals(contentType)){
                        fileMeta.setName(name);
                        fileMeta.setSize(String.valueOf(size));
                        fileMeta.setType(contentType);
                        fileMeta.setError("文件格式有误,请看底部说明");
                        fileMeta.setStatus("400");
                        files.add(fileMeta);
                        result.setFiles(files.toArray());
                        response.getWriter().println(JsonUtils.objectToJson(result));
                        return;
                    }
                    System.out.println("文件的大小: "+size);
                    System.out.println("文件的类型: "+contentType);
                    System.out.println("文件的名字: "+name);
                    System.out.println("表单项name属性名: "+fieldName);
                    fileMeta.setName(name);
                    fileMeta.setSize(String.valueOf(size));
                    fileMeta.setType(contentType);
                    fileMeta.setStatus("200");
                    resultJson.addProperty("name", name);
                    resultJson.addProperty("size", size);
                    resultJson.addProperty("type", contentType);
                    //resultJson1.add("files",[]);
                    //获取ServletContext对象
                    ServletContext context = this.getServletContext();
                    //获取项目的真实路径
                    String path = context.getRealPath("/WEB-INF/uploadFile");

                    //判断路径是否存在
                    File file = new File(path);
                    if(!file.exists()){
                        //如果不存在该路径，则创建一个路径
                        file.mkdirs();
                    }

                    //将文件写入到磁盘中
                    fileItem.write(new File(path+"/"+name));
                    files.add(fileMeta);

                }

                //result.setFiles(files.toArray());

            }
            if (files.size()>0){
                result.setFiles(files.toArray());
                result.setName("test");
                resultJson.addProperty("files", JsonUtils.objectToJson(files));
                response.getWriter().println(JsonUtils.objectToJson(result));
                return;
            }


            //重定向到一个页面
            //response.sendRedirect(request.getContextPath()+"/success.jsp");

        }catch(FileUploadBase.FileSizeLimitExceededException e){
            //一但捕获到该异常，则说明单个文件大小超过限制。
            //设置一个错误消息
            //request.setAttribute("msg", "单个文件大小请不要超过50KB");

            fileMeta.setName("不合法文件");
            fileMeta.setSize(String.valueOf(-1));
            fileMeta.setType("too big");
            fileMeta.setError("单个文件大小请不要超过50KB");
            fileMeta.setStatus("400");
            files.add(fileMeta);
            result.setFiles(files.toArray());

            response.getWriter().println(JsonUtils.objectToJson(result));
            return;

            //转发到index.jsp
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch(FileUploadBase.SizeLimitExceededException e){
            //一但捕获到该异常，则说明单个文件大小超过限制。
            //设置一个错误消息
            //request.setAttribute("msg", "所有文件大小请不要超过15M");
            //转发到index.jsp
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //response.getWriter().println(resultJson);
        //response.getWriter().println();
        //return;


    }




}
