package az.test.repository.implementation;

import az.test.db.DbHelper;
import az.test.db.JdbcUtility;
import az.test.domain.ClickHistory;
import az.test.domain.FlexibleList;
import az.test.domain.FlexibleListItem;
import az.test.domain.TokenHistory;
import az.test.domain.enums.Status;
import az.test.domain.enums.TokenType;
import az.test.repository.CommonDao;

import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CommonDaoImpl implements CommonDao {
    @Override
    public List<FlexibleList> getAllFlexibleList() {
        List<FlexibleList> allFlexibleList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_ALL_FLEXIBLE_LIST);
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                Optional<FlexibleList> optionalFlexibleList = getFlexibleListById(id);
                if (optionalFlexibleList.isPresent()) {
                    allFlexibleList.add(optionalFlexibleList.get());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }

        return allFlexibleList;
    }

    @Override
    public Optional<FlexibleList> getFlexibleListById(long id) {
        Optional<FlexibleList> optionalFlexibleList = Optional.empty();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_FLEXIBLE_LIST_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            FlexibleList flexibleList = read(rs, ps, connection);
            if (flexibleList != null) {
                optionalFlexibleList = Optional.of(flexibleList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return optionalFlexibleList;
    }

    @Override
    public Optional<FlexibleList> getFlexibleListByName(String name) {
        Optional<FlexibleList> optionalFlexibleList = Optional.empty();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_FLEXIBLE_LIST_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            FlexibleList flexibleList = read(rs, ps, connection);

            if (flexibleList != null) {
                optionalFlexibleList = Optional.of(flexibleList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return optionalFlexibleList;
    }

    @Override
    public long getItemId(String item) {
        long itemId=0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_ITEM_ID);
            ps.setString(1,item);
            rs=ps.executeQuery();
            if(rs.next()){
                itemId=rs.getLong("value_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
       return itemId;
    }

    @Override
    public void addTokenHistory(TokenHistory tokenHistory) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection = DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.ADD_TOKEN_HISTORY);
            ps.setLong(1,tokenHistory.getUser_id());
            ps.setString(2,tokenHistory.getToken());
            ps.setInt(3, tokenHistory.getToken_type());
            ps.setTimestamp(4, Timestamp.valueOf(tokenHistory.getInsertDate()));
            ps.setInt(5,Status.toValue(Status.ACTIVE));
            ps.setTimestamp(6,Timestamp.valueOf(tokenHistory.getTokenExpireDate()));
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            JdbcUtility.close(rs, ps, connection);
        }

    }

    @Override
    public void addClickHistory(ClickHistory clickHistory) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection = DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.ADD_CLICK_HISTORY);
            ps.setString(1,clickHistory.getToken());
            ps.setLong(2,clickHistory.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(clickHistory.getInsertDate()));
            ps.setString(4, clickHistory.getIp());
            ps.setInt(5,Status.toValue(Status.ACTIVE));
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            JdbcUtility.close(rs, ps, connection);
        }

    }

    @Override
    public Optional<TokenHistory> getTokenHistoryByToken(String token) {
        Optional<TokenHistory> optionalTokenHistory=Optional.empty();
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection = DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.GET_TOKEN_HISTORY_BY_ID);
            ps.setString(1,token);
            rs=ps.executeQuery();
            while (rs.next()){
                TokenHistory tokenHistory=new TokenHistory();
                tokenHistory.setId(rs.getLong("id"));
                tokenHistory.setUser_id(rs.getLong("user_id"));
                tokenHistory.setToken(rs.getString("token"));
                tokenHistory.setToken_type(rs.getInt("token_type"));
                tokenHistory.setInsertDate(rs.getTimestamp("insert_date").toLocalDateTime());
                tokenHistory.setTokenExpireDate(rs.getTimestamp("token_expire_date").toLocalDateTime());
                optionalTokenHistory=Optional.of(tokenHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return optionalTokenHistory;
    }


    public FlexibleList read(ResultSet rs, PreparedStatement ps, Connection connection) throws SQLException {
        FlexibleList flexibleList = null;
        if (rs.next()) {
            flexibleList =new FlexibleList();
            flexibleList.setId(rs.getLong("id"));
            flexibleList.setName(rs.getString("name"));
            flexibleList.setStatus(Status.ACTIVE);
            ps = connection.prepareStatement(SqlQuery.GET_FLEXIBLE_LIST_ITEM_BY_IIST_ID);
            ps.setLong(1, flexibleList.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                FlexibleListItem item = new FlexibleListItem();
                item.setId(rs.getLong("id"));
                item.setListId(flexibleList.getId());
                item.setValueId(rs.getLong("value_id"));
                item.setValueName(rs.getString("value_name"));
                item.setInsertDate(rs.getTimestamp("insert_date").toLocalDateTime());
                flexibleList.add(item);
            }
        }
        return flexibleList;
    }
}
