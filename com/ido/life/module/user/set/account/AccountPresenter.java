package com.ido.life.module.user.set.account;

import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.ThirdLogin;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.set.account.AccountContract;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AccountPresenter implements AccountContract.Presenter {
    public static String[] thirdNames = {"WEIXIN", "QQ", "FACEBOOK", "GOOGLE", "TWITTER"};
    private String mAccount = "";
    private List<ThirdLogin> mLoginList = new ArrayList();
    private AccountContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public AccountPresenter(AccountContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.Presenter
    public void initData() {
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            this.mAccount = userInfo.getEmail();
            this.mView.showUserAccount(this.mAccount);
        }
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.Presenter
    public void loginOut() {
        this.mView.loginOutSuccess();
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.Presenter
    public void queryThirdAccount() {
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            this.mAccount = userInfo.getEmail();
        }
        this.mView.showLoading();
        AccountRepository.getInstance().queryThirdAccount(this.mAccount, new AccountManager.OnCommCallback<List<ThirdLogin>>() { // from class: com.ido.life.module.user.set.account.AccountPresenter.1
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(List<ThirdLogin> list) {
                AccountPresenter.this.mView.hideLoading();
                if (list == null || list.size() <= 0) {
                    return;
                }
                AccountPresenter.this.mLoginList.clear();
                AccountPresenter.this.mLoginList.addAll(list);
                AccountPresenter accountPresenter = AccountPresenter.this;
                accountPresenter.checkThirdLogin(accountPresenter.mLoginList);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                AccountPresenter.this.mView.hideLoading();
                AccountPresenter.this.mView.showMessage(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void checkThirdLogin(List<ThirdLogin> list) {
        for (int i = 0; i < list.size(); i++) {
            String accountType = list.get(i).getAccountType();
            byte b2 = -1;
            switch (accountType.hashCode()) {
                case -1738246558:
                    if (accountType.equals("WEIXIN")) {
                        b2 = 0;
                    }
                    break;
                case -198363565:
                    if (accountType.equals("TWITTER")) {
                        b2 = 3;
                    }
                    break;
                case 2592:
                    if (accountType.equals("QQ")) {
                        b2 = 1;
                    }
                    break;
                case 1279756998:
                    if (accountType.equals("FACEBOOK")) {
                        b2 = 2;
                    }
                    break;
                case 2108052025:
                    if (accountType.equals("GOOGLE")) {
                        b2 = 4;
                    }
                    break;
            }
            if (b2 == 0) {
                this.mView.setCheckWeChat(true);
            } else if (b2 == 1) {
                this.mView.setCheckQQ(true);
            } else if (b2 == 2) {
                this.mView.setCheckFacebook(true);
            } else if (b2 == 3) {
                this.mView.setCheckTwitter(true);
            } else if (b2 == 4) {
                this.mView.setCheckGoogle(true);
            }
        }
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.Presenter
    public void cancelThirdAccount(String str) {
        this.mView.showLoading();
        AccountRepository.getInstance().thirdLogin(str, new ThirdLoginManager.OnThirdCallback() { // from class: com.ido.life.module.user.set.account.AccountPresenter.2
            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onSuccess(String str2, AccountManager.AuthData authData) {
                AccountPresenter.this.toCancelThirdAccount(str2, authData.openid, AccountPresenter.this.mAccount);
            }

            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onFailed(String str2) {
                AccountPresenter.this.mView.hideLoading();
                AccountPresenter.this.mView.showMessage(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toCancelThirdAccount(final String str, String str2, String str3) {
        AccountRepository.getInstance().cancelThirdAccount(str2, str3, new AccountManager.OnCommCallback<Boolean>() { // from class: com.ido.life.module.user.set.account.AccountPresenter.3
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(Boolean bool) {
                AccountPresenter.this.mView.hideLoading();
                for (int i = 0; i < AccountPresenter.thirdNames.length; i++) {
                    if (str.equals(AccountPresenter.thirdNames[i])) {
                        if (i == 0) {
                            AccountPresenter.this.mView.setCheckWeChat(bool.booleanValue());
                        } else if (i == 1) {
                            AccountPresenter.this.mView.setCheckQQ(bool.booleanValue());
                        } else if (i == 2) {
                            AccountPresenter.this.mView.setCheckFacebook(bool.booleanValue());
                        } else if (i == 3) {
                            AccountPresenter.this.mView.setCheckTwitter(bool.booleanValue());
                        } else if (i == 4) {
                            AccountPresenter.this.mView.setCheckGoogle(bool.booleanValue());
                        }
                    }
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str4) {
                AccountPresenter.this.mView.hideLoading();
                AccountPresenter.this.mView.showMessage(str4);
                for (int i = 0; i < AccountPresenter.thirdNames.length; i++) {
                    if (str.equals(AccountPresenter.thirdNames[i])) {
                        if (i == 0) {
                            AccountPresenter.this.mView.setCheckWeChat(true);
                        } else if (i == 1) {
                            AccountPresenter.this.mView.setCheckQQ(true);
                        } else if (i == 2) {
                            AccountPresenter.this.mView.setCheckFacebook(true);
                        } else if (i == 3) {
                            AccountPresenter.this.mView.setCheckTwitter(true);
                        } else if (i == 4) {
                            AccountPresenter.this.mView.setCheckGoogle(true);
                        }
                    }
                }
            }
        });
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.Presenter
    public void getOpenId(final String str) {
        this.mView.showLoading();
        AccountRepository.getInstance().thirdLogin(str, new ThirdLoginManager.OnThirdCallback() { // from class: com.ido.life.module.user.set.account.AccountPresenter.4
            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onSuccess(String str2, AccountManager.AuthData authData) {
                AccountPresenter.this.bindThirdAccount(str, authData.openid, AccountPresenter.this.mAccount);
            }

            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onFailed(String str2) {
                AccountPresenter.this.mView.hideLoading();
                AccountPresenter.this.mView.showMessage(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(final String str, String str2, String str3) {
        AccountRepository.getInstance().bindThirdAccount(str2, str3, new AccountManager.OnCommCallback<String>() { // from class: com.ido.life.module.user.set.account.AccountPresenter.5
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(String str4) {
                AccountPresenter.this.mView.hideLoading();
                ThirdLogin thirdLogin = new ThirdLogin();
                for (int i = 0; i < AccountPresenter.thirdNames.length; i++) {
                    if (str.equals(AccountPresenter.thirdNames[i])) {
                        if (i == 0) {
                            AccountPresenter.this.mView.setCheckWeChat(true);
                            thirdLogin.setAccountType(AccountPresenter.thirdNames[0]);
                            if (!AccountPresenter.this.mLoginList.contains(thirdLogin)) {
                                AccountPresenter.this.mLoginList.add(thirdLogin);
                            }
                        } else if (i == 1) {
                            AccountPresenter.this.mView.setCheckQQ(true);
                            thirdLogin.setAccountType(AccountPresenter.thirdNames[1]);
                            if (!AccountPresenter.this.mLoginList.contains(thirdLogin)) {
                                AccountPresenter.this.mLoginList.add(thirdLogin);
                            }
                        } else if (i == 2) {
                            AccountPresenter.this.mView.setCheckFacebook(true);
                            thirdLogin.setAccountType(AccountPresenter.thirdNames[2]);
                            if (!AccountPresenter.this.mLoginList.contains(thirdLogin)) {
                                AccountPresenter.this.mLoginList.add(thirdLogin);
                            }
                        } else if (i == 3) {
                            thirdLogin.setAccountType(AccountPresenter.thirdNames[1]);
                            if (!AccountPresenter.this.mLoginList.contains(thirdLogin)) {
                                AccountPresenter.this.mLoginList.add(thirdLogin);
                            }
                            AccountPresenter.this.mView.setCheckTwitter(true);
                        } else if (i == 4) {
                            thirdLogin.setAccountType(AccountPresenter.thirdNames[1]);
                            if (!AccountPresenter.this.mLoginList.contains(thirdLogin)) {
                                AccountPresenter.this.mLoginList.add(thirdLogin);
                            }
                            AccountPresenter.this.mView.setCheckGoogle(true);
                        }
                    }
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str4) {
                AccountPresenter.this.mView.hideLoading();
                AccountPresenter.this.mView.showMessage(str4);
                for (int i = 0; i < AccountPresenter.thirdNames.length; i++) {
                    if (str.equals(AccountPresenter.thirdNames[i])) {
                        if (i == 0) {
                            AccountPresenter.this.mView.setCheckWeChat(false);
                        } else if (i == 1) {
                            AccountPresenter.this.mView.setCheckQQ(false);
                        } else if (i == 2) {
                            AccountPresenter.this.mView.setCheckFacebook(false);
                        } else if (i == 3) {
                            AccountPresenter.this.mView.setCheckTwitter(false);
                        } else if (i == 4) {
                            AccountPresenter.this.mView.setCheckGoogle(false);
                        }
                    }
                }
            }
        });
    }

    private void clearUserInfo() {
        AccountRepository.getInstance().clearUserInfo();
    }
}