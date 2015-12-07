package Demo1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Demo1.Buyer;
import Demo1.Realitor;
import Demo1.Land;
import Demo1.Mortgage;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author yunfan
 */
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    IndexWriter w;
    StandardAnalyzer analyzer;
    IndexWriterConfig config;
    Directory index;
    searchHistory log;
    ArrayList<Buyer> stds = new ArrayList<Buyer>();
    ArrayList<Realitor> tchs = new ArrayList<Realitor>();
    Buyer currentStudent;
    List courseList;

    public void init() throws ServletException {
        try {
            //	Specify the analyzer for tokenizing text.
            //	The same analyzer should be used for indexing and searching
            analyzer = new StandardAnalyzer();
            //	Code to create the index
            index = new RAMDirectory();
            config = new IndexWriterConfig(analyzer);
            w = new IndexWriter(index, config);
            addDoc(w, " San Mateo Warhouse", "500,000", "San Mateo", "Business");
            addDoc(w, " Growing Land", "1,000,000", "Fresno.", "Business");
            addDoc(w, "Apartment complex", "1,356,999", "San Jose.", "Economy");
            addDoc(w, " SuperMarkett ", "200,000", "Milpitas.", "Business");
            addDoc(w, " White House", "2,000,000:", "Washington.", "Economy");
            addDoc(w, "Desert land ", "200,000", "Santa Cruz.", "Personal");
            w.close();
            log = new searchHistory();
            for(int i=1;i<=10;i++){
                Buyer std = new Buyer();
                std.setUserName("std"+i);
                std.setPassword("123");
                stds.add(std);
                Realitor tch = new Realitor();
                tch.setUserName("tch"+i);
                tch.setPassword("123");
                tchs.add(tch);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("init");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            if (request.getParameter("func").equals("search")) {
                gotoSearch(out, request, response);
            } else if (request.getParameter("func").equals("login")) {
                gotoLogin(out, request, response);
            } else if (request.getParameter("func").equals("Mortgage")) {
                gotoEnroll(out, request, response);
            } else if (request.getParameter("func").equals("addLand")) {
                gotoAddCourse(out, request, response);
            } else if (request.getParameter("func").equals("log")) {
                String msg = log.printHistory();
                String title = "Log";
                gotoMsg(out, request, response, title, msg);
            } else if (request.getParameter("func").equals("clear")) {
                currentStudent.getMortgage().getArr().clear();
                currentStudent.getProperty().getArr().clear();
                String msg = "Clear land searched for";
                String title = "No lands";
                gotoMsg(out, request, response, title, msg);
            }
            else {
                String msg = "No Page Found";
                String title = "Error";
                gotoMsg(out, request, response, title, msg);
            }
        }
    }

    private static void addDoc(IndexWriter w, String Name, String Price, String Area, String Purpose) throws IOException {
        Document doc = new Document();
        // A text field will be tokenized
        doc.add(new StringField("name", Name, Field.Store.YES));
        // We use a string field for isbn because we don\'t want it tokenized
        doc.add(new StringField("price", Price, Field.Store.YES));
        doc.add(new StringField("area", Area, Field.Store.YES));
        doc.add(new StringField("purpose", Purpose, Field.Store.YES));
        String Searching = Name +" "+ Price+ " "+ Area+" "+Purpose;
        doc.add(new TextField("Searching", Searching, Field.Store.NO));
        w.addDocument(doc);
    }

    private void gotoSearch(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        try {
            //	Text to search
            String querystr = request.getParameter("keyword");

            log.addHistory(querystr);

            //	The \"title\" arg specifies the default field to use when no field is explicitly specified in the query
            Query q = new QueryParser("Searching", analyzer).parse(querystr);

            // Searching code
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            //	Code to display the results of search
            //out.println("Found " + hits.length + " Classes Matching your Requirement");
            courseList = new ArrayList();
            for (int i = 0; i < hits.length; ++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                Land course = new Land(d.get("name"), d.get("price"), d.get("area"), d.get("purpose"));
                //out.println((i + 1) + ". " +  d.get("Number")+ d.get("Classes") );
                courseList.add(course);
            }
            request.setAttribute("Lands", courseList);
            RequestDispatcher de = request.getRequestDispatcher("/table.jsp");
            de.forward(request, response);

            // reader can only be closed when there is no need to access the documents any more
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotoLogin(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String psd = request.getParameter("psd");

        if (stds != null) {
            for (int i = 0; i < stds.size(); i++) {
                Buyer s = stds.get(i);
                System.out.println("std\n");
                if (name.equals(s.getUserName()) && psd.equals(s.getPassword())) {
                    try {
                        currentStudent = s;
                        RequestDispatcher de = request.getRequestDispatcher("/search.html");
                        de.forward(request, response);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        if (tchs != null) {
            for (int i = 0; i < tchs.size(); i++) {
                Realitor t = tchs.get(i);
                if (name.equals(t.getUserName()) && psd.equals(t.getPassword())) {
                    try {
                        RequestDispatcher de = request.getRequestDispatcher("/submit.html");
                        de.forward(request, response);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        String msg = "Wrong Name or Password";
        String title = "Error";
        gotoMsg(out, request, response, title, msg);
    }

    private void gotoMsg(PrintWriter out, HttpServletRequest request, HttpServletResponse response, String title, String msg) {
        try {
            request.setAttribute("msg", msg);
            request.setAttribute("title", title);
            RequestDispatcher de = request.getRequestDispatcher("/message.jsp");
            de.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void gotoEnroll(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (currentStudent != null) {
            for (int i = 0; i < courseList.size(); i++) {
                Land c = (Land) courseList.get(i);
                Mortgage enroll = currentStudent.getMortgage();
                ArrayList<Object> arr = enroll.getArr();
                if (c.getName().equals(request.getParameter("name"))) {
                    if (currentStudent.getClause().check(arr)) {
                        currentStudent.getMortgage().addCourse(c);
                        currentStudent.getProperty().addLand(c);
                        String msg = "You choose this land " + request.getParameter("area") + ": " + request.getParameter("name");
                        msg+= "<hr>Choose Land<br>" + currentStudent.getProperty().print();
                        String title = "Land choice success";
                        gotoMsg(out, request, response, title, msg);
                    } else {
                        String msg = "You cannot purchase this land " + request.getParameter("area") + ": "
                                + request.getParameter("name") + "<br>Since you have 4 lands";
                        msg+= "<hr>Choose Land<br>" + currentStudent.getProperty().print();
                        String title = "land registration failed";
                        gotoMsg(out, request, response, title, msg);
                    }
                }
            }
        }
        String msg = "You cannot choose this land" + request.getParameter("area") + ": " + request.getParameter("name");
        String title = "Land failed Failed";
        msg+= "<hr>Choose Land<br>" + currentStudent.getProperty().print();
        gotoMsg(out, request, response, title, msg);
    }

    private void gotoAddCourse(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String id = request.getParameter("area");
        String department = request.getParameter("purpose");
        String time = request.getParameter("price");
        try {
            IndexWriterConfig newConfig = new IndexWriterConfig(analyzer);
            w = new IndexWriter(index, newConfig);
            addDoc(w, name, id, time, department);
            System.out.println(name);
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String msg = "Create land success";
        String title = "Create land success";
        gotoMsg(out, request, response, title, msg);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
