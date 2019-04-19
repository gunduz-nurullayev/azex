package az.test.repository.implementation;

public class SqlQuery {
    public static final String GET_EMPLOYEE_LIST = "select id, name, age, address, salary, status from employees where status=1";
    public static final String GET_EMPLOYEE_LIST_BY_ID = "select id, name, age, address, salary, status from employees where status =1 and id=?";
    public static final String ADD_EMPLOYEE = "insert into employees(name,age,address,salary, status) values(?,?,?,?,?)";
    public static final String AUTHENTICATE_USER = "select id, name,surname, email,password, status from azex_user where email=? and password=? and status=1 and user_status=1 ";
    public static final String INSERT_USER = "insert into azex_user(id,name,surname,email,registration_date, token, token_expire_date, password,user_type, user_status, status) " +
            " values(nextval('azex_user_seq'),?, ?, ?,?,?,?,?, ?,?,1)";
    public static final String INSERT_USER_ROLE = "insert into user_role(id,user_id,role_id,status) " +
            "VALUES(nextval('user_role_seq'),?,?,1)";

    public static final String GET_USER_ROLES = "select ur.role_id, r.name, " +
            " r.default_controller, r.default_page from azex_user u " +
            "join user_role ur on u.id = ur.user_id and ur.status = 1 " +
            "join role r on r.id = ur.role_id and r.status = 1 " +
            "where u.id=?";
    public static final String GET_USER_DATA_BY_EMAIL = "SELECT id, name, surname, email, token from azex_user where email=? and status=1";
    public static final String GET_ALL_FLEXIBLE_LIST = "select id,name,status from flexible_list where status =1";

    public static final String GET_FLEXIBLE_LIST_BY_ID = "select id,name,status from flexible_list where id=? and status=1";

    public static final String GET_FLEXIBLE_LIST_BY_NAME = "select id,name,status from flexible_list where name=? and status=1";

    public static final String GET_FLEXIBLE_LIST_ITEM_BY_IIST_ID = "select fli.id, fli.value_id, fli.value_name, fli.insert_date " +
            "from flexible_list fl join flexible_list_item fli on fl.id = fli.list_id " +
            "   and fl.status = 1 and fli.status = 1 " +
            "where fl.id = ? " +
            "order by fli.value_id";
    public static final String GET_FLEXIBLE_LIST_ITEMS_BY_LIST_NAME = "select fli.id, fli.value_id, fli.value_name, fli.insert_date " +
            "from flexible_list fl join flexible_list_item fli on fl.id = fli.list_id " +
            "   and fl.status = 1 and fli.status = 1 " +
            "where fl.name = ? " +
            "order by fli.value_id";

    public static final String CHECK_TOKEN = "select id, name, surname, email, token, token_expire_date from azex_user where token=? and status=1";
    public static final String UPDATE_TOKEN = "update azex_user " +
            "set token=?,token_expire_date=? " +
            "where id=? and status=1";

    public static final String GET_ITEM_ID = "select value_id from flexible_list_item where value_name=? and status=1";

    public static final String ADD_USER_COMPLETE = "update azex_user  " +
            "set id_card_prefix=?,id_card_number=?,pin_code=?,gender=?, " +
            "phone1=?,phone2=?, birth_date=?,activation_date=?, user_status=? " +
            "where email=? and status=1";

    public static final String ADD_CUSTOMER = "update customer " +
            "set customer_code=?, channel=?, referral_code=?,city=?,district=?,address=? " +
            "where id=? and status=1";

    public static final String GET_USER_ID = "select id from azex_user where token=? and status=1";

    public static final String ADD_TOKEN_HISTORY = "insert into token_history(id,user_id,token, token_type,insert_date,status,token_expire_date) " +
            "values(nextval('token_history_seq'),?,?,?,?,?,?)";
    public static final String ADD_CLICK_HISTORY = "insert into click_history(id,token,user_id, insert_date,ip,status)  " +
            "values(nextval('click_history_seq'),?,?,?,?,?)";
    public static final String GET_TOKEN_HISTORY_BY_ID = "SELECT id, user_id, token, token_type, insert_date, token_expire_date from token_history  " +
            "where token=? and status=1 ";
    public static final String GET_USER_DATA_BY_ID = "select name,surname,email from azex_user where id=? and status=1 and user_status=1";
    public static final String UPDATE_PASSWORD = "update azex_user " +
            "set password=? " +
            "where email=? and status=1 and user_status=1";

    public static  final  String GET_CUSTOMER_DATA_BY_USER_ID="select id, customer_code, channel, referral_code, city, district, address from customer " +
            " where user_id=? and status=1";

    public static final String CREATE_BALANCE = "insert into balance(id, amount, currency_id,customer_id,last_update, status) " +
            "values(nextval('balance_seq'),?,?,?,?,?)";
    public static final String GET_BALANCE_BY_CUSTOMER_ID = "select amount, currency_id from balance where customer_id=?\n";
    public static final String GET_CUSTOMER_LIST =
            "select c.id, u.name, u.surname , u.email, u.birth_date,  u.id_card_prefix, u.id_card_number, " +
                    "u.pin_code, c.customer_code, flcity.value_name as city " +
                    "from customer c  " +
                    "join azex_user u on c.user_id=u.id " +
                    "join (select * from flexible_list_item " +
                    "where list_id=2) flcity on c.city=flcity.value_id " +
                    "where c.status=1 and (u.name like ? or u.surname like ?) " +
                    "limit ? " +
                    "OFFSET ?" ;
    public static final String GET_CUSTOMER_COUNT = "select count(c.id)from customer c " +
            "join azex_user u on c.user_id=u.id " +
            "join (select * from flexible_list_item " +
            "where list_id=2) flcity on c.city=flcity.value_id " +
            "where c.status=1 and ( u.name like ? or u.surname like ?)";
    public static final String GET_USER_DATA_BY_CUSTOMER_ID = "select c.id, u.name, u.surname,u.email,u.birth_date, u.id_card_prefix, u.id_card_number, u.pin_code,c.customer_code,c.city from azex_user u " +
            "join customer c on c.user_id=u.id " +
            "where c.id=? and c.status=1 and u.status=1" ;
}


