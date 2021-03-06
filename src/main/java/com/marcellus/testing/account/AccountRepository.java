package com.marcellus.testing.account;

import java.util.List;

public interface AccountRepository {

    List<Account> getAllAccounts();
    List<String> getByNames(String name);
}
