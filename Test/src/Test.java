import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Test {

    public static void main(String[] args) {
        JFrame frame =new JFrame();
        JFrame signup_frame =new JFrame("Create Account");
        JFrame login_frame=new JFrame("Login");
        JPanel panel=new JPanel(new FlowLayout());
        JPanel create_Acc=new JPanel(new FlowLayout());
        JPanel login_Acc =new JPanel(new FlowLayout());
        JButton create_new_Acc =new JButton("Create new Account");
        JButton sign_up=new JButton("Signup");
        JButton Signin =new JButton("Signin");
        JButton login=new JButton("Login");
        JTextField uname_field_create=new JTextField(30);
        JTextField pass_field_create=new JTextField(30);
        JTextField uname_field_login=new JTextField(30);
        JPasswordField pass_field_login=new JPasswordField(30);
        JLabel uname =new JLabel("User Name");
        JLabel pass =new JLabel("Password");
        JLabel msg=new JLabel();
        panel.add(create_new_Acc);
        panel.add(Signin);
        frame.add(panel);
        create_Acc.add(uname);
        create_Acc.add(uname_field_create);
        create_Acc.add(pass);
        create_Acc.add(pass_field_create);
        create_Acc.add(sign_up);
        signup_frame.add(create_Acc);
        signup_frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        create_new_Acc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signup_frame.setVisible(true);
                sign_up.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)  {
                        try{
                            System.out.println("SignIn ActionPerformed!");
                            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Desktop\\project.db");
                            String create_table="create table if not exists login(username  text primary key,password  text )";
                            Statement stmt=con.createStatement();
                            stmt.execute(create_table);
                            int temp=1;
                            try{
                                System.out.println("In try block");
                            if((uname_field_create.getText().toString()).equals(null) || (pass_field_create.getText().toString()).equals(null)){
                                System.out.println("In if Part Before throwing Exception");
                                throw (new SQLException());
                            }
                            }catch(SQLException check_validcred){
                                System.out.println("In exceptional case");
                                JOptionPane.showMessageDialog(signup_frame,"Enter Valid Username and Password");
                                uname_field_create.setText(null);pass_field_create.setText(null);
                                temp=0;
                                stmt.close();
                            }
                            System.out.println("Before !");
                            if(temp==1){
                                System.out.println("In Acc created");
                                System.out.println("uname='"+uname_field_create.getText()+"'");
                                System.out.println("pass='"+pass_field_create.getText()+"'");
                                System.out.println("");
                                String insert="insert into login values('"+uname_field_create.getText()+"','"+pass_field_create.getText()+"')";
                                stmt.execute(insert);
                                uname_field_create.setText(null);
                                pass_field_create.setText(null);
                                signup_frame.dispose();
                                JOptionPane.showMessageDialog(null,"Account created");
                                stmt.close();
                            }
                        }catch(SQLException se){
                            JOptionPane.showMessageDialog(null,"Username Already Exists!");
                            uname_field_create.setText(null);
                            pass_field_create.setText(null);
                            signup_frame.dispose();
                        }
                        
                    }
                });
            }
        });
        Signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                login_Acc.add(uname);
                login_Acc.add(uname_field_login);
                login_Acc.add(pass);
                login_Acc.add(pass_field_login);
                login_Acc.add(login);
                login_frame.add(login_Acc);
                login_frame.pack();
                login_frame.setVisible(true);
                login.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                         try{
                            System.out.println("in actionperformed");
                            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Desktop\\project.db");
                            int temp=1;
                            String check_usernameQuery="select username from login where username='"+uname_field_login.getText()+"'";
                            String check_passwordQuery="select password from login where username='"+uname_field_login.getText()+"'";
                            StringBuilder uname_field_login_value=new StringBuilder(uname_field_login.getText());
                            StringBuilder pass_field_login_value=new StringBuilder(pass_field_login.getText());
                            StringBuilder check_username=new StringBuilder("");
                            StringBuilder check_password=new StringBuilder("");
                            Statement stmt;ResultSet rst;
                            
                            try{
                            stmt=con.createStatement();
                            rst=stmt.executeQuery(check_usernameQuery);
                            rst.next();
                            check_username.append(rst.getString("username"));
                            rst=stmt.executeQuery(check_passwordQuery);
                            rst.next();
                            check_password.append(rst.getString("password"));
                            }catch(SQLException check_cred){
                                JOptionPane.showMessageDialog(frame,"Invalid Username or Password");
                                temp=0;uname_field_login.setText(null);pass_field_login.setText(null);
                            }
                            if(temp!=0 && uname_field_login_value.toString().equals(check_username.toString()) && pass_field_login_value.toString().equals(check_password.toString())){
                                System.out.println("In if part!");
                                String cat[]={"--Select--","Friends/Family","Business"};
                                String role[]={"--Select--","Stockist","Distributor","C&F Agent","Industry","Mutual","Govt"};
                                JFrame succ_login_frame =new JFrame(uname_field_login.getText()+" Home");
                                JTabbedPane tbd=new JTabbedPane();
                                JPanel create_cont_panel=new JPanel();create_cont_panel.setLayout(null);
                                JPanel view_cont_pane=new JPanel(new BorderLayout());
                                JPanel search_cont_pane=new JPanel(new BorderLayout());
                                JPanel view_cat_pane=new JPanel();
                                JPanel view_table_pane=new JPanel(new BorderLayout());
                                JPanel search_table_pane=new JPanel(new BorderLayout());
                                JPanel search_cat_pane=new JPanel();
                                JComboBox<String> role_dropdown=new JComboBox<>(role);
                                JComboBox<String> cat_dropdown=new JComboBox<String>(cat); cat_dropdown.setBounds(129,23,102,20);
                                JComboBox<String> view_cat_dropdown=new JComboBox<>(cat);
                                JComboBox<String> search_cat_dropdown=new JComboBox<>(cat);
                                JLabel name_label=new JLabel("Name");name_label.setBounds(51,71,78,20);
                                JLabel number_label=new JLabel("Number");number_label.setBounds(51,110,78,20);
                                JLabel landline_label=new JLabel("LandLine");landline_label.setBounds(51,155,78,20);
                                JLabel add_label=new JLabel("Address");add_label.setBounds(51,196,78,20);
                                JLabel city_label=new JLabel("City");city_label.setBounds(51,235,78,20);
                                JLabel pin_label=new JLabel("Pincode");pin_label.setBounds(51,274,78,20);
                                JTextField name_field=new JTextField(20);name_field.setBounds(236,71,102,20);
                                JTextField number_field=new JTextField(20);number_field.setBounds(236,110,102,20);
                                JTextField landline_field=new JTextField(20);landline_field.setBounds(236,155,102,20);
                                JTextField add_field=new JTextField(20);add_field.setBounds(236,196,102,20);
                                JTextField city_field=new JTextField(20);city_field.setBounds(236,235,102,20);
                                JTextField pin_field=new JTextField(20);pin_field.setBounds(236,274,102,20);
                                JTextField search_field=new JTextField(50);
                                JButton save_Button=new JButton("Save");save_Button.setBounds(139,314,90,20);
                                JLabel web_label=new JLabel("Web");
                                JLabel email_label=new JLabel("Email");
                                JLabel gst_label=new JLabel("GST");
                                JLabel fax_label=new JLabel("Fax");
                                JLabel result_label=new JLabel("No Records Found");
                                JTextField web_field=new JTextField(20);
                                JTextField email_field=new JTextField(20);
                                JTextField gst_field=new JTextField(20);
                                JTextField fax_field=new JTextField(20);
                                JTable result_table=new JTable();
                                JTable search_result_table=new JTable();
                                JScrollPane jsp=new JScrollPane(result_table);
                                JScrollPane jsp1=new JScrollPane(search_result_table);
                                JButton delete_btn=new JButton("Delete");
                                JButton edit_btn=new JButton("Edit");
                                JButton search_btn=new JButton("Search");
                                result_table.setAutoCreateRowSorter(true);
                                search_result_table.setAutoCreateRowSorter(true);
                                create_cont_panel.add(cat_dropdown);
                                create_cont_panel.add(name_label);create_cont_panel.add(name_field);
                                create_cont_panel.add(number_label);create_cont_panel.add(number_field);
                                create_cont_panel.add(landline_label);create_cont_panel.add(landline_field);
                                create_cont_panel.add(add_label);create_cont_panel.add(add_field);
                                create_cont_panel.add(city_label);create_cont_panel.add(city_field);
                                create_cont_panel.add(pin_label);create_cont_panel.add(pin_field);
                                create_cont_panel.add(save_Button);
                                create_cont_panel.setBackground(Color.ORANGE);
                                
                                
                                view_cat_pane.add(view_cat_dropdown);
                                view_cat_pane.add(delete_btn);
                                view_cat_pane.add(edit_btn);
                                view_cont_pane.add(view_cat_pane,BorderLayout.NORTH);
                                view_table_pane.add(jsp);
                                view_cont_pane.add(view_table_pane);
                                
                                search_cat_pane.add(search_cat_dropdown);
                                search_cat_pane.add(search_field);
                                search_cat_pane.add(search_btn);
                                search_table_pane.add(jsp1);
                                search_cont_pane.add(new JPanel().add(search_cat_pane),BorderLayout.NORTH);
                                search_cont_pane.add(search_table_pane);
                                
                                search_result_table.setModel(new DefaultTableModel());
                                
                                search_cat_dropdown.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        search_result_table.setModel(new DefaultTableModel());
                                    }
                                });
                                
                                search_btn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        if(search_cat_dropdown.getSelectedItem().equals("--Select--")){
                                            JOptionPane.showMessageDialog(null,"Select Valid Category to Search!");
                                            search_result_table.setModel(new DefaultTableModel());
                                        }
                                        else if(search_cat_dropdown.getSelectedItem().equals("Friends/Family")){
                                            String key= search_field.getText();
                                            if(key.isEmpty()){JOptionPane.showMessageDialog(null,"Enter Text to search");}
                                            else{
                                            try{
                                                Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                                PreparedStatement pst;ResultSet rs=null;
                                                String get_result="select * from frifam where name like '%"+key+"%'";
                                                pst=con.prepareStatement(get_result);
                                                rs=pst.executeQuery();
                                                search_result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                                if(search_result_table.getRowCount()==0){
                                                    JOptionPane.showMessageDialog(null,"No records Found","Search Result:",JOptionPane.WARNING_MESSAGE);
                                                }
                                            }catch(SQLException se){}
                                            }
                                        }
                                        else{
                                            String key= search_field.getText();
                                            if(key.isEmpty()){JOptionPane.showMessageDialog(null,"Enter Text to search");}
                                            else{
                                            try{
                                                Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                                PreparedStatement pst;ResultSet rs=null;
                                                String get_result="select * from business where name like '%"+key+"%'";
                                                pst=con.prepareStatement(get_result);
                                                rs=pst.executeQuery();
                                                search_result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                                if(search_result_table.getRowCount()==0){
                                                    JOptionPane.showMessageDialog(null,"No records Found","Search Result:",JOptionPane.WARNING_MESSAGE);
                                                }
                                            }catch(SQLException se){}
                                            }
                                        }
                                    }
                                });
                                
                                edit_btn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try{
                                            DefaultTableModel model=(DefaultTableModel)result_table.getModel();
                                            int row=result_table.getSelectedRow();
                                            String number_val=String.valueOf(result_table.getValueAt(row, 1));
                                            String table_val=String.valueOf(view_cat_dropdown.getSelectedItem());
                                            int col=result_table.getSelectedColumn();
                                            String old_data=String.valueOf(result_table.getValueAt(row, col));
                                            String col_name=result_table.getColumnName(col);
                                            String new_data=JOptionPane.showInputDialog(null,"Enter new data into "+result_table.getColumnName(col)+" for selected record");
                                            if(new_data==null){new_data=old_data;}
                                            if(table_val.equals("Friends/Family")){table_val="frifam";}
                                            String sql="update "+table_val+" set "+col_name+"='"+new_data+"' where number='"+number_val+"'";
        //once test all possible cases after doind editing part!!
                                            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                            Statement stmt=con.createStatement();
                                            stmt.execute(sql);
                                            PreparedStatement pst;ResultSet rs=null;
                                            String get_result="select * from "+table_val;
                                            pst=con.prepareStatement(get_result);
                                            rs=pst.executeQuery();
                                            result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                        }catch(Exception ex){
                                            JOptionPane.showMessageDialog(null, "Select data to Edit");
                                        }
                                    }
                                });
                                
                                
                                
                                delete_btn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try{
                                        DefaultTableModel model=(DefaultTableModel)result_table.getModel();
                                        int row=result_table.getSelectedRow();
                                        String number_val=String.valueOf(result_table.getValueAt(row, 1));
                                        String table_val=String.valueOf(view_cat_dropdown.getSelectedItem());
                                        if(table_val.equals("Friends/Family")){table_val="frifam";}
                                        System.out.println("Phone: "+number_val);
                                        System.out.println("Table: "+table_val);
                                        Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                        Statement stmt=con.createStatement();
                                        System.out.println("flag1");
                                        System.out.println("delete from "+table_val+" where number='"+number_val+"'");
                                        stmt.execute("delete from "+table_val+" where number='"+number_val+"'");
                                        System.out.println("flag2");
                                        PreparedStatement pst;ResultSet rs=null;
                                        String get_result="select * from "+table_val;
                                        pst=con.prepareStatement(get_result);
                                        rs=pst.executeQuery();
                                        result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                        }catch(Exception ex){
                                            JOptionPane.showMessageDialog(null,"Select data to Delete");
                                        }}
                                });
                                
                                result_table.setModel(new DefaultTableModel());
                                
                                view_cat_dropdown.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        JComboBox test=(JComboBox)e.getSource();
                                        String check_view_cat_selected=(String)test.getSelectedItem();
                                        if(check_view_cat_selected.equals("Friends/Family")){
                                            try{
                                            Connection tempcon=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                            PreparedStatement pst;ResultSet rs=null;
                                            String get_result="select * from frifam";
                                            pst=tempcon.prepareStatement(get_result);
                                            rs=pst.executeQuery();
                                            result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                            TableColumnModel tcm=result_table.getColumnModel();
                                                for(int i=0;i<6;i++){
                                                    tcm.getColumn(i).setMinWidth(100);
                                                }
                                            }catch(SQLException viewE){
                                                System.out.println("Exception: "+viewE.getMessage());
                                                view_cat_dropdown.setSelectedItem("--Select--");
                                                JOptionPane.showMessageDialog(null,"No Records Found");
                                                result_table.setModel(new DefaultTableModel());
                                            }
                                        }
                                        else if(check_view_cat_selected.equals("Business")){
                                            try{
                                                
                                            Connection tempcon=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db");
                                            PreparedStatement pst;ResultSet rs=null;
                                            String get_result="select * from business";
                                            System.out.println("Flag");
                                            pst=tempcon.prepareStatement(get_result); 
                                            rs=pst.executeQuery();    
                                            result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                            TableColumnModel tcm=result_table.getColumnModel();
                                                for(int i=0;i<11;i++){
                                                    tcm.getColumn(i).setMinWidth(100);
                                                }
                                            }catch(SQLException viewE){
                                                System.out.println("Exception: "+viewE.getMessage());
                                                view_cat_dropdown.setSelectedItem("--Select--");
                                                JOptionPane.showMessageDialog(null,"No Records Found");
                                                result_table.setModel(new DefaultTableModel());
                                            }
                                        }
                                        //You Left Here!
                                        else{result_table.setModel(new DefaultTableModel());}
                                    }
                                });
                                
                                
                                tbd.add("Create Contact",create_cont_panel);
                                tbd.add("View Contact",view_cont_pane);
                                tbd.add("Search Contact",search_cont_pane);
                                System.out.println("Initially selected option is "+cat_dropdown.getSelectedItem());
                                login_frame.dispose();
                                succ_login_frame.add(tbd);
                                succ_login_frame.setVisible(true);
                                succ_login_frame.setSize(800,830);
                                succ_login_frame.setDefaultCloseOperation(succ_login_frame.EXIT_ON_CLOSE);
                                cat_dropdown.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                       JComboBox jcb=(JComboBox)e.getSource();
                                       String check_selected=(String)jcb.getSelectedItem();
                                       if(check_selected.equals("Business")){
                                           create_cont_panel.add(role_dropdown);role_dropdown.setBounds(134,314,102,20);
                                           create_cont_panel.add(web_label);web_label.setBounds(51,354,78,30);
                                           create_cont_panel.add(email_label);email_label.setBounds(51,394,78,20);
                                           create_cont_panel.add(web_field);web_field.setBounds(238,354,102,20);
                                           create_cont_panel.add(email_field);email_field.setBounds(238,394,102,20);
                                           save_Button.setBounds(139,500,90,20);
                                           succ_login_frame.pack();
                                           succ_login_frame.setSize(800,830);
                                           role_dropdown.addActionListener(new ActionListener() {
                                               public void actionPerformed(ActionEvent e) {
                                                   JComboBox JCB=(JComboBox)e.getSource();
                                                   String check_role=(String)JCB.getSelectedItem();
                                                   if(check_role.equals("Stockist") || check_role.equals("Distributor") || check_role.equals("C&F Agent") || check_role.equals("Industry")){
                                                       create_cont_panel.add(gst_label);gst_label.setBounds(51,434,78,20);
                                                       create_cont_panel.add(fax_label);fax_label.setBounds(51,474,78,20);
                                                       create_cont_panel.add(gst_field);gst_field.setBounds(238,434,102,20);
                                                       create_cont_panel.add(fax_field);fax_field.setBounds(238,474,102,20);
                                                       succ_login_frame.pack();
                                                       succ_login_frame.setSize(800,830);
                                                   }
                                                   else{
                                                       create_cont_panel.remove(gst_label);create_cont_panel.remove(gst_field);
                                                       create_cont_panel.remove(fax_label);create_cont_panel.remove(fax_field);
                                                       succ_login_frame.pack();
                                                       succ_login_frame.setSize(800,830);
                                                   }
                                               }
                                           });
                                       }
                                       else if(check_selected.equals("Friends/Family") || check_selected.equals("--Select--")){
                                           create_cont_panel.remove(role_dropdown);
                                           create_cont_panel.remove(web_label);create_cont_panel.remove(web_field);
                                           create_cont_panel.remove(email_label);create_cont_panel.remove(email_field);
                                           create_cont_panel.remove(gst_label);create_cont_panel.remove(gst_field);
                                           create_cont_panel.remove(fax_label);create_cont_panel.remove(fax_field);
                                           save_Button.setBounds(139,314,90,20);
                                           succ_login_frame.pack();
                                           succ_login_frame.setSize(800,830);
                                           role_dropdown.setSelectedItem("--Select--");
                                       }
                                    }
                                });
                                //start here
                                save_Button.addMouseListener(new MouseAdapter() {
                                    public void mouseEntered(MouseEvent me){
                                        save_Button.setBackground(Color.green);
                                    }
                                    public void mouseExited(MouseEvent me1){
                                        save_Button.setBackground(Color.white);
                                    }
                                });
                                //end here
                                save_Button.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        char check_chars[]="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+-=\",./;'[]{}:?<>|\\".toCharArray();
                                        String check_name=name_field.getText();
                                        String check_number=number_field.getText();
                                        String check_landline=landline_field.getText();
                                        if((cat_dropdown.getSelectedItem()).equals("Friends/Family")){    
                                            if(check_name.equals("") || check_number.equals("")){
                                                JOptionPane.showMessageDialog(null,"Name & Number Fields are mandatory");
                                            }
                                            else{
                                                int temp=-1;
                                                temp=CheckAlph.containsAny(check_number,check_chars)?1:0;
                                                System.out.println("temp is "+temp);
                                                if(check_number.length()==10 && temp==0){
                                                    String user_name="'"+check_name+"'";String user_number="'"+check_number+"'";
                                                    String user_landline;String user_add;
                                                    String user_city;String user_pin;
                                                    temp=CheckAlph.containsAny(check_landline,check_chars)?1:0;
                                                    int length=check_landline.length();
                                                    if(temp==0 && (length==0 || length==8)){
                                                        user_landline="'"+check_landline+"'";
                                                        user_add="'"+add_field.getText()+"'";
                                                        user_city="'"+city_field.getText()+"'";
                                                        user_pin="'"+pin_field.getText()+"'";
                                                        int check_exe=ContactsDataBase.familyData(uname_field_login.getText(), user_name, user_number, user_landline, user_add, user_city, user_pin);
                                                        if(check_exe==1){
                                                            JOptionPane.showMessageDialog(null, "Contact created!");
                                                            try{
                                                                String sql="jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db";
                                                                Connection con1=DriverManager.getConnection(sql);
                                                                PreparedStatement pst;ResultSet rs=null;
                                                                String get_result="select * from frifam";
                                                                pst=con1.prepareStatement(get_result);
                                                                rs=pst.executeQuery();
                                                                result_table.setModel(DbUtils.resultSetToTableModel(rs));
  //you are hERe-->                                             
                                                                view_cat_dropdown.setSelectedItem("--Select--");
                                                            }catch(Exception e1){}
                                                        }
                                                        else{JOptionPane.showMessageDialog(null, "Cannot create Contact");}
                                                    }
                                                    else{JOptionPane.showMessageDialog(null,"Invalid Landline Field!");}
                                                }else{
                                                    JOptionPane.showMessageDialog(null,"Enter Valid Mobile Number");
                                                }
                                            }
                                        }
                                        else if((cat_dropdown.getSelectedItem()).equals("Business")){
                                            if(check_name.equals("") || check_number.equals("")){
                                                JOptionPane.showMessageDialog(null, "Name & Number fields are mandatroy");
                                            }else{
                                                int temp=-1,temp1=-1;
                                                temp=CheckAlph.containsAny(check_number,check_chars)?1:0;
                                                temp1=CheckAlph.containsAny(check_landline, check_chars)?1:0;
                                                if(check_number.length()==10 && temp==0 && temp1==0 && (check_landline.length()==8 || check_landline.length()==0)){
                                                    String check_role=role_dropdown.getSelectedItem().toString();
                                                    String user_name="'"+check_name+"'";String user_number="'"+check_number+"'";
                                                    String user_landline="'"+check_landline+"'";
                                                    String user_email="'"+email_field.getText()+"'";
                                                    String user_web="'"+web_field.getText()+"'";
                                                    String user_role="'"+role_dropdown.getSelectedItem().toString()+"'";
                                                    String user_add="'"+add_field.getText()+"'";String user_city="'"+city_field.getText()+"'";
                                                    String user_pin="'"+pin_field.getText()+"'";    
                                                    if(check_role.equals("Govt") || check_role.equals("Mutual")){
                                                        int check_exe=ContactsDataBase.businessData(user_role,uname_field_login.getText(), user_name, user_number, user_landline, user_add, user_city, user_pin, user_web, user_email,"''","''");
                                                        if(check_exe==1){
                                                            JOptionPane.showMessageDialog(null,"Contact created!");
                                                            try{
                                                                String sql="jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db";
                                                                Connection con1=DriverManager.getConnection(sql);
                                                                PreparedStatement pst;ResultSet rs=null;
                                                                String get_result="select * from business";
                                                                pst=con1.prepareStatement(get_result);
                                                                rs=pst.executeQuery();
                                                                result_table.setModel(DbUtils.resultSetToTableModel(rs));
                                                                view_cat_dropdown.setSelectedItem("--Select--");
                                                            }catch(Exception e1){}
                                                        }
                                                        else{JOptionPane.showMessageDialog(null,"Cannot create Contact");}
                                                    }
                                                    else if(check_role.equals("--Select--")){
                                                        JOptionPane.showMessageDialog(null,"Select valid Role");
                                                    }
                                                    else{
                                                        if((gst_field.getText().toString().length()==12 || gst_field.getText().toString().length()==0) && (fax_field.getText().toString().length()==0 || fax_field.getText().toString().length()==8)){
                                                            String user_gst="'"+gst_field.getText()+"'";
                                                            String user_fax="'"+fax_field.getText()+"'";
                                                            int check_exe=ContactsDataBase.businessData(user_role,uname_field_login.getText(), user_name, user_number, user_landline, user_add, user_city, user_pin, user_web, user_email,user_gst,user_fax);
                                                            if(check_exe==1){
                                                                JOptionPane.showMessageDialog(null,"Contact created!");
                                                                try{
                                                                String sql="jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+uname_field_login.getText()+".db";
                                                                Connection con1=DriverManager.getConnection(sql);
                                                                PreparedStatement pst;ResultSet rs=null;
                                                                String get_result="select * from business";
                                                                pst=con1.prepareStatement(get_result);
                                                                rs=pst.executeQuery();
                                                                result_table.setModel(DbUtils.resultSetToTableModel(rs));
  //you are hERe-->
                                                                }catch(Exception e1){}
                                                            }
                                                            else{JOptionPane.showMessageDialog(null,"Cannot create Contact");}
                                                        }
                                                        else{JOptionPane.showMessageDialog(null, "Enter valid gst/fax number!");}
                                                    }                       
                                                }
                                                else{JOptionPane.showMessageDialog(null, "Ente valid Number/Landline");}
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null,"Select Valid Category!");
                                        }
                                    }
                                });
                            }else{
                                if(temp!=0){JOptionPane.showMessageDialog(frame,"Invalid Username or Password");
                                uname_field_login.setText(null);pass_field_login.setText(null);
                                }
                            }
                        }catch(SQLException se){
                          
                        }
                    }
                });
            }
        }); 
    }
    public static class CheckAlph{
        public static boolean containsAny(String str, char[] searchChars) {
            if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
             return false;
            }
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                for (int j = 0; j < searchChars.length; j++) {
                    if (searchChars[j] == ch) {
                    return true;
                    }
                }
            }
          return false;
        }
    }
}

class ContactsDataBase{
    public static int familyData(String user,String name,String number,String landline,String add,String city,String pin){
        try{
            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+user+".db");
            Statement stmt=con.createStatement();
            stmt.execute("create table if not exists frifam(name text,number text primary key,landline text,address text,city text,picode text)");
            if(landline.equals("''")){landline=null;}
            if(add.equals("''")){add=null;}if(city.equals("''")){city=null;}if(pin.equals("''")){pin=null;}
            String insert_frifam="insert into frifam values("+name+","+number+","+landline+","+add+","+city+","+pin+")";
            stmt.execute(insert_frifam);
            return 1;
        }catch(SQLException sqlexcp){
            System.out.println("1Exception: "+sqlexcp.getMessage());
            return 0;
        }
    }
    public static int businessData(String role,String user,String name,String number,String landline,String add,String city,String pin,String web,String email,String gst,String fax){
        try{
            Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SRIKANT\\Documents\\NetBeansProjects\\Test\\DataBases\\"+user+".db");
            Statement stmt=con.createStatement();
            stmt.execute("create table if not exists business(name text,number text primary key,landline text,address text,city text,pincode text,role text,web text,email text,gst text,fax text)");
            if(landline.equals("''")){landline=null;}
            if(add.equals("''")){add=null;}if(city.equals("''")){city=null;}if(pin.equals("''")){pin=null;}
            if(web.equals("''")){web=null;}if(email.equals("''")){email=null;}if(gst.equals("''")){gst=null;}
            if(fax.equals("''")){fax=null;}
            String insert_business="insert into business values("+name+","+number+","+landline+","+add+","+city+","+pin+","+role+","+web+","+email+","+gst+","+fax+")";
            stmt.execute(insert_business);
            System.out.println(insert_business);
            return 1;
        }catch(SQLException sqlexcp){
            System.out.println("Exception: "+sqlexcp.getMessage());
            return 0;
        }
    }
}