package az.test.service.implementation;

import az.test.domain.ClickHistory;
import az.test.domain.FlexibleList;
import az.test.domain.TokenHistory;
import az.test.repository.CommonDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.service.CommonService;

import java.util.List;
import java.util.Optional;

public class CommonServiceImpl implements CommonService {
    private CommonDao commonDao;

    public CommonServiceImpl(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public List<FlexibleList> getAllFlexibleList() {
        return commonDao.getAllFlexibleList();
    }

    @Override
    public Optional<FlexibleList> getFlexibleListById(long id) {
        return commonDao.getFlexibleListById(id);
    }

    @Override
    public Optional<FlexibleList> getFlexibleListByName(String name) {
        return commonDao.getFlexibleListByName(name);
    }

    @Override
    public long getItemId(String item) {
        return commonDao.getItemId(item);
    }

    @Override
    public void addTokenHistory(TokenHistory tokenHistory) {
        commonDao.addTokenHistory(tokenHistory);
    }

    @Override
    public void addClickHistory(ClickHistory clickHistory) {
        commonDao.addClickHistory(clickHistory);
    }

    @Override
    public Optional<TokenHistory> getTokenHistoryByToken(String token) {
        return commonDao.getTokenHistoryByToken(token);
    }


}
