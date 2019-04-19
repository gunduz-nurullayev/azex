package az.test.service;

import az.test.domain.ClickHistory;
import az.test.domain.FlexibleList;
import az.test.domain.TokenHistory;

import java.util.List;
import java.util.Optional;

public interface CommonService {
    List<FlexibleList> getAllFlexibleList();
    Optional<FlexibleList> getFlexibleListById(long id);
    Optional<FlexibleList> getFlexibleListByName(String name);
    long getItemId(String item);
    void addTokenHistory(TokenHistory tokenHistory);
    void addClickHistory(ClickHistory clickHistory);
    Optional<TokenHistory> getTokenHistoryByToken(String token);


}
