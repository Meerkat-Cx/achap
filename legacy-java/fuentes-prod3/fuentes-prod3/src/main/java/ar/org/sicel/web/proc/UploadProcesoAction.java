package ar.org.sicel.web.proc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.proc.Main;
import ar.org.sicel.util.Sicel3Conf;

public class UploadProcesoAction extends Action {
	
	static final long serialVersionUID	=1L;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm arg1, HttpServletRequest req, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//public boolean procesaFicheros (HttpServletRequest req, HttpServletResponse response) throws IOException {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*20);
			factory.setRepository(new File("/tmp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024*1024*10); //Hay que establecer un maximo permitido a subir?
			List items = upload.parseRequest(req);
            if(items == null) return mapping.findForward("error");
            Iterator i = items.iterator();
            byte[] data = null;
            while (i.hasNext())
            {
            	FileItem item=(FileItem)i.next();
            	if (!item.isFormField()) {
            		data = item.get();
            	}
            	else {
            		//items que no son archivos, ejemplo un campo de texto
            		//item.getName()
            	}
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmss");
            String PROC_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild(
            "destinoDir").getValue();
            String path =  PROC_DIR + "entradas/" + "entrada_" + sdf.format(new Date()) + ".xml";
            
            File salida = new File(path);
            FileOutputStream out = new FileOutputStream(salida);
            String entrada = IOUtils.toString(new ByteArrayInputStream(data));
            out.write(entrada.getBytes());
            out.flush();
            out.close();
            
            Runnable proceso = new ProcesoJob(path);
            Thread thread = new Thread(proceso);
            thread.start();
            //proceso.run();
            
            return mapping.findForward("ok");
            
        }
		catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("error");
        }

    }
}


class ProcesoJob implements Runnable {
	
	String filename;

	public void run() {
		Main main = new Main();
		main.doEjecutar(filename);
	}
	
	public ProcesoJob(String fileName){
		this.filename = fileName;
	}
}
	
