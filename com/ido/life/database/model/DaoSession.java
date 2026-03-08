package com.ido.life.database.model;

import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DaoSession extends AbstractDaoSession {
    private final ActiveTimeDayDataDao activeTimeDayDataDao;
    private final DaoConfig activeTimeDayDataDaoConfig;
    private final AgreementPrivacyVersionDao agreementPrivacyVersionDao;
    private final DaoConfig agreementPrivacyVersionDaoConfig;
    private final CalorieDayDataDao calorieDayDataDao;
    private final DaoConfig calorieDayDataDaoConfig;
    private final DataDownLoadStateDao dataDownLoadStateDao;
    private final DaoConfig dataDownLoadStateDaoConfig;
    private final DataPullConfigInfoDao dataPullConfigInfoDao;
    private final DaoConfig dataPullConfigInfoDaoConfig;
    private final GpsColorsDao gpsColorsDao;
    private final DaoConfig gpsColorsDaoConfig;
    private final HealthPressureDao healthPressureDao;
    private final DaoConfig healthPressureDaoConfig;
    private final HealthVolumeDataDao healthVolumeDataDao;
    private final DaoConfig healthVolumeDataDaoConfig;
    private final HomeCardDao homeCardDao;
    private final DaoConfig homeCardDaoConfig;
    private final LifeCycleItemBeanDao lifeCycleItemBeanDao;
    private final DaoConfig lifeCycleItemBeanDaoConfig;
    private final MapEngineDao mapEngineDao;
    private final DaoConfig mapEngineDaoConfig;
    private final MenstruationConfigDao menstruationConfigDao;
    private final DaoConfig menstruationConfigDaoConfig;
    private final MessageEntityDao messageEntityDao;
    private final DaoConfig messageEntityDaoConfig;
    private final MultiDaysWalkTotalDataDao multiDaysWalkTotalDataDao;
    private final DaoConfig multiDaysWalkTotalDataDaoConfig;
    private final NoticeAppEntityDao noticeAppEntityDao;
    private final DaoConfig noticeAppEntityDaoConfig;
    private final PrivateSafeSettingDao privateSafeSettingDao;
    private final DaoConfig privateSafeSettingDaoConfig;
    private final ServerBloodOxyDayDataDao serverBloodOxyDayDataDao;
    private final DaoConfig serverBloodOxyDayDataDaoConfig;
    private final ServerBloodOxyMonthDataDao serverBloodOxyMonthDataDao;
    private final DaoConfig serverBloodOxyMonthDataDaoConfig;
    private final ServerDaysBloodOxyDataDao serverDaysBloodOxyDataDao;
    private final DaoConfig serverDaysBloodOxyDataDaoConfig;
    private final ServerDaysSleepDataDao serverDaysSleepDataDao;
    private final DaoConfig serverDaysSleepDataDaoConfig;
    private final ServerHeartRateDayDataDao serverHeartRateDayDataDao;
    private final DaoConfig serverHeartRateDayDataDaoConfig;
    private final ServerMultiMonthBloodOxyTotalDataDao serverMultiMonthBloodOxyTotalDataDao;
    private final DaoConfig serverMultiMonthBloodOxyTotalDataDaoConfig;
    private final ServerMultiMonthSleepTotalDataDao serverMultiMonthSleepTotalDataDao;
    private final DaoConfig serverMultiMonthSleepTotalDataDaoConfig;
    private final ServerSleepDayDataDao serverSleepDayDataDao;
    private final DaoConfig serverSleepDayDataDaoConfig;
    private final ServerSleepMonthDataDao serverSleepMonthDataDao;
    private final DaoConfig serverSleepMonthDataDaoConfig;
    private final SportCardDao sportCardDao;
    private final DaoConfig sportCardDaoConfig;
    private final SportDistanceBeanDao sportDistanceBeanDao;
    private final DaoConfig sportDistanceBeanDaoConfig;
    private final SportGpsDataDao sportGpsDataDao;
    private final DaoConfig sportGpsDataDaoConfig;
    private final SportHealthDao sportHealthDao;
    private final DaoConfig sportHealthDaoConfig;
    private final StepDayDataDao stepDayDataDao;
    private final DaoConfig stepDayDataDaoConfig;
    private final TempUnitSettingDao tempUnitSettingDao;
    private final DaoConfig tempUnitSettingDaoConfig;
    private final TimeSetDao timeSetDao;
    private final DaoConfig timeSetDaoConfig;
    private final UnitSettingDao unitSettingDao;
    private final DaoConfig unitSettingDaoConfig;
    private final UserInfoDao userInfoDao;
    private final DaoConfig userInfoDaoConfig;
    private final UserMedalInfoDao userMedalInfoDao;
    private final DaoConfig userMedalInfoDaoConfig;
    private final UserTargetNewDao userTargetNewDao;
    private final DaoConfig userTargetNewDaoConfig;
    private final WalkDayDataDao walkDayDataDao;
    private final DaoConfig walkDayDataDaoConfig;
    private final WalkMonthTotalDataDao walkMonthTotalDataDao;
    private final DaoConfig walkMonthTotalDataDaoConfig;
    private final WalkYearTotalDataDao walkYearTotalDataDao;
    private final DaoConfig walkYearTotalDataDaoConfig;
    private final WeekStartSettingDao weekStartSettingDao;
    private final DaoConfig weekStartSettingDaoConfig;
    private final WeightItemBeanDao weightItemBeanDao;
    private final DaoConfig weightItemBeanDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.activeTimeDayDataDaoConfig = map.get(ActiveTimeDayDataDao.class).clone();
        this.activeTimeDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.agreementPrivacyVersionDaoConfig = map.get(AgreementPrivacyVersionDao.class).clone();
        this.agreementPrivacyVersionDaoConfig.initIdentityScope(identityScopeType);
        this.calorieDayDataDaoConfig = map.get(CalorieDayDataDao.class).clone();
        this.calorieDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.dataDownLoadStateDaoConfig = map.get(DataDownLoadStateDao.class).clone();
        this.dataDownLoadStateDaoConfig.initIdentityScope(identityScopeType);
        this.dataPullConfigInfoDaoConfig = map.get(DataPullConfigInfoDao.class).clone();
        this.dataPullConfigInfoDaoConfig.initIdentityScope(identityScopeType);
        this.gpsColorsDaoConfig = map.get(GpsColorsDao.class).clone();
        this.gpsColorsDaoConfig.initIdentityScope(identityScopeType);
        this.healthPressureDaoConfig = map.get(HealthPressureDao.class).clone();
        this.healthPressureDaoConfig.initIdentityScope(identityScopeType);
        this.healthVolumeDataDaoConfig = map.get(HealthVolumeDataDao.class).clone();
        this.healthVolumeDataDaoConfig.initIdentityScope(identityScopeType);
        this.homeCardDaoConfig = map.get(HomeCardDao.class).clone();
        this.homeCardDaoConfig.initIdentityScope(identityScopeType);
        this.lifeCycleItemBeanDaoConfig = map.get(LifeCycleItemBeanDao.class).clone();
        this.lifeCycleItemBeanDaoConfig.initIdentityScope(identityScopeType);
        this.mapEngineDaoConfig = map.get(MapEngineDao.class).clone();
        this.mapEngineDaoConfig.initIdentityScope(identityScopeType);
        this.menstruationConfigDaoConfig = map.get(MenstruationConfigDao.class).clone();
        this.menstruationConfigDaoConfig.initIdentityScope(identityScopeType);
        this.messageEntityDaoConfig = map.get(MessageEntityDao.class).clone();
        this.messageEntityDaoConfig.initIdentityScope(identityScopeType);
        this.multiDaysWalkTotalDataDaoConfig = map.get(MultiDaysWalkTotalDataDao.class).clone();
        this.multiDaysWalkTotalDataDaoConfig.initIdentityScope(identityScopeType);
        this.noticeAppEntityDaoConfig = map.get(NoticeAppEntityDao.class).clone();
        this.noticeAppEntityDaoConfig.initIdentityScope(identityScopeType);
        this.privateSafeSettingDaoConfig = map.get(PrivateSafeSettingDao.class).clone();
        this.privateSafeSettingDaoConfig.initIdentityScope(identityScopeType);
        this.serverBloodOxyDayDataDaoConfig = map.get(ServerBloodOxyDayDataDao.class).clone();
        this.serverBloodOxyDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverBloodOxyMonthDataDaoConfig = map.get(ServerBloodOxyMonthDataDao.class).clone();
        this.serverBloodOxyMonthDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverDaysBloodOxyDataDaoConfig = map.get(ServerDaysBloodOxyDataDao.class).clone();
        this.serverDaysBloodOxyDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverDaysSleepDataDaoConfig = map.get(ServerDaysSleepDataDao.class).clone();
        this.serverDaysSleepDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverHeartRateDayDataDaoConfig = map.get(ServerHeartRateDayDataDao.class).clone();
        this.serverHeartRateDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverMultiMonthBloodOxyTotalDataDaoConfig = map.get(ServerMultiMonthBloodOxyTotalDataDao.class).clone();
        this.serverMultiMonthBloodOxyTotalDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverMultiMonthSleepTotalDataDaoConfig = map.get(ServerMultiMonthSleepTotalDataDao.class).clone();
        this.serverMultiMonthSleepTotalDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverSleepDayDataDaoConfig = map.get(ServerSleepDayDataDao.class).clone();
        this.serverSleepDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.serverSleepMonthDataDaoConfig = map.get(ServerSleepMonthDataDao.class).clone();
        this.serverSleepMonthDataDaoConfig.initIdentityScope(identityScopeType);
        this.sportCardDaoConfig = map.get(SportCardDao.class).clone();
        this.sportCardDaoConfig.initIdentityScope(identityScopeType);
        this.sportDistanceBeanDaoConfig = map.get(SportDistanceBeanDao.class).clone();
        this.sportDistanceBeanDaoConfig.initIdentityScope(identityScopeType);
        this.sportGpsDataDaoConfig = map.get(SportGpsDataDao.class).clone();
        this.sportGpsDataDaoConfig.initIdentityScope(identityScopeType);
        this.sportHealthDaoConfig = map.get(SportHealthDao.class).clone();
        this.sportHealthDaoConfig.initIdentityScope(identityScopeType);
        this.stepDayDataDaoConfig = map.get(StepDayDataDao.class).clone();
        this.stepDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.tempUnitSettingDaoConfig = map.get(TempUnitSettingDao.class).clone();
        this.tempUnitSettingDaoConfig.initIdentityScope(identityScopeType);
        this.timeSetDaoConfig = map.get(TimeSetDao.class).clone();
        this.timeSetDaoConfig.initIdentityScope(identityScopeType);
        this.unitSettingDaoConfig = map.get(UnitSettingDao.class).clone();
        this.unitSettingDaoConfig.initIdentityScope(identityScopeType);
        this.userInfoDaoConfig = map.get(UserInfoDao.class).clone();
        this.userInfoDaoConfig.initIdentityScope(identityScopeType);
        this.userMedalInfoDaoConfig = map.get(UserMedalInfoDao.class).clone();
        this.userMedalInfoDaoConfig.initIdentityScope(identityScopeType);
        this.userTargetNewDaoConfig = map.get(UserTargetNewDao.class).clone();
        this.userTargetNewDaoConfig.initIdentityScope(identityScopeType);
        this.walkDayDataDaoConfig = map.get(WalkDayDataDao.class).clone();
        this.walkDayDataDaoConfig.initIdentityScope(identityScopeType);
        this.walkMonthTotalDataDaoConfig = map.get(WalkMonthTotalDataDao.class).clone();
        this.walkMonthTotalDataDaoConfig.initIdentityScope(identityScopeType);
        this.walkYearTotalDataDaoConfig = map.get(WalkYearTotalDataDao.class).clone();
        this.walkYearTotalDataDaoConfig.initIdentityScope(identityScopeType);
        this.weekStartSettingDaoConfig = map.get(WeekStartSettingDao.class).clone();
        this.weekStartSettingDaoConfig.initIdentityScope(identityScopeType);
        this.weightItemBeanDaoConfig = map.get(WeightItemBeanDao.class).clone();
        this.weightItemBeanDaoConfig.initIdentityScope(identityScopeType);
        this.activeTimeDayDataDao = new ActiveTimeDayDataDao(this.activeTimeDayDataDaoConfig, this);
        this.agreementPrivacyVersionDao = new AgreementPrivacyVersionDao(this.agreementPrivacyVersionDaoConfig, this);
        this.calorieDayDataDao = new CalorieDayDataDao(this.calorieDayDataDaoConfig, this);
        this.dataDownLoadStateDao = new DataDownLoadStateDao(this.dataDownLoadStateDaoConfig, this);
        this.dataPullConfigInfoDao = new DataPullConfigInfoDao(this.dataPullConfigInfoDaoConfig, this);
        this.gpsColorsDao = new GpsColorsDao(this.gpsColorsDaoConfig, this);
        this.healthPressureDao = new HealthPressureDao(this.healthPressureDaoConfig, this);
        this.healthVolumeDataDao = new HealthVolumeDataDao(this.healthVolumeDataDaoConfig, this);
        this.homeCardDao = new HomeCardDao(this.homeCardDaoConfig, this);
        this.lifeCycleItemBeanDao = new LifeCycleItemBeanDao(this.lifeCycleItemBeanDaoConfig, this);
        this.mapEngineDao = new MapEngineDao(this.mapEngineDaoConfig, this);
        this.menstruationConfigDao = new MenstruationConfigDao(this.menstruationConfigDaoConfig, this);
        this.messageEntityDao = new MessageEntityDao(this.messageEntityDaoConfig, this);
        this.multiDaysWalkTotalDataDao = new MultiDaysWalkTotalDataDao(this.multiDaysWalkTotalDataDaoConfig, this);
        this.noticeAppEntityDao = new NoticeAppEntityDao(this.noticeAppEntityDaoConfig, this);
        this.privateSafeSettingDao = new PrivateSafeSettingDao(this.privateSafeSettingDaoConfig, this);
        this.serverBloodOxyDayDataDao = new ServerBloodOxyDayDataDao(this.serverBloodOxyDayDataDaoConfig, this);
        this.serverBloodOxyMonthDataDao = new ServerBloodOxyMonthDataDao(this.serverBloodOxyMonthDataDaoConfig, this);
        this.serverDaysBloodOxyDataDao = new ServerDaysBloodOxyDataDao(this.serverDaysBloodOxyDataDaoConfig, this);
        this.serverDaysSleepDataDao = new ServerDaysSleepDataDao(this.serverDaysSleepDataDaoConfig, this);
        this.serverHeartRateDayDataDao = new ServerHeartRateDayDataDao(this.serverHeartRateDayDataDaoConfig, this);
        this.serverMultiMonthBloodOxyTotalDataDao = new ServerMultiMonthBloodOxyTotalDataDao(this.serverMultiMonthBloodOxyTotalDataDaoConfig, this);
        this.serverMultiMonthSleepTotalDataDao = new ServerMultiMonthSleepTotalDataDao(this.serverMultiMonthSleepTotalDataDaoConfig, this);
        this.serverSleepDayDataDao = new ServerSleepDayDataDao(this.serverSleepDayDataDaoConfig, this);
        this.serverSleepMonthDataDao = new ServerSleepMonthDataDao(this.serverSleepMonthDataDaoConfig, this);
        this.sportCardDao = new SportCardDao(this.sportCardDaoConfig, this);
        this.sportDistanceBeanDao = new SportDistanceBeanDao(this.sportDistanceBeanDaoConfig, this);
        this.sportGpsDataDao = new SportGpsDataDao(this.sportGpsDataDaoConfig, this);
        this.sportHealthDao = new SportHealthDao(this.sportHealthDaoConfig, this);
        this.stepDayDataDao = new StepDayDataDao(this.stepDayDataDaoConfig, this);
        this.tempUnitSettingDao = new TempUnitSettingDao(this.tempUnitSettingDaoConfig, this);
        this.timeSetDao = new TimeSetDao(this.timeSetDaoConfig, this);
        this.unitSettingDao = new UnitSettingDao(this.unitSettingDaoConfig, this);
        this.userInfoDao = new UserInfoDao(this.userInfoDaoConfig, this);
        this.userMedalInfoDao = new UserMedalInfoDao(this.userMedalInfoDaoConfig, this);
        this.userTargetNewDao = new UserTargetNewDao(this.userTargetNewDaoConfig, this);
        this.walkDayDataDao = new WalkDayDataDao(this.walkDayDataDaoConfig, this);
        this.walkMonthTotalDataDao = new WalkMonthTotalDataDao(this.walkMonthTotalDataDaoConfig, this);
        this.walkYearTotalDataDao = new WalkYearTotalDataDao(this.walkYearTotalDataDaoConfig, this);
        this.weekStartSettingDao = new WeekStartSettingDao(this.weekStartSettingDaoConfig, this);
        this.weightItemBeanDao = new WeightItemBeanDao(this.weightItemBeanDaoConfig, this);
        registerDao(ActiveTimeDayData.class, this.activeTimeDayDataDao);
        registerDao(AgreementPrivacyVersion.class, this.agreementPrivacyVersionDao);
        registerDao(CalorieDayData.class, this.calorieDayDataDao);
        registerDao(DataDownLoadState.class, this.dataDownLoadStateDao);
        registerDao(DataPullConfigInfo.class, this.dataPullConfigInfoDao);
        registerDao(GpsColors.class, this.gpsColorsDao);
        registerDao(HealthPressure.class, this.healthPressureDao);
        registerDao(HealthVolumeData.class, this.healthVolumeDataDao);
        registerDao(HomeCard.class, this.homeCardDao);
        registerDao(LifeCycleItemBean.class, this.lifeCycleItemBeanDao);
        registerDao(MapEngine.class, this.mapEngineDao);
        registerDao(MenstruationConfig.class, this.menstruationConfigDao);
        registerDao(MessageEntity.class, this.messageEntityDao);
        registerDao(MultiDaysWalkTotalData.class, this.multiDaysWalkTotalDataDao);
        registerDao(NoticeAppEntity.class, this.noticeAppEntityDao);
        registerDao(PrivateSafeSetting.class, this.privateSafeSettingDao);
        registerDao(ServerBloodOxyDayData.class, this.serverBloodOxyDayDataDao);
        registerDao(ServerBloodOxyMonthData.class, this.serverBloodOxyMonthDataDao);
        registerDao(ServerDaysBloodOxyData.class, this.serverDaysBloodOxyDataDao);
        registerDao(ServerDaysSleepData.class, this.serverDaysSleepDataDao);
        registerDao(ServerHeartRateDayData.class, this.serverHeartRateDayDataDao);
        registerDao(ServerMultiMonthBloodOxyTotalData.class, this.serverMultiMonthBloodOxyTotalDataDao);
        registerDao(ServerMultiMonthSleepTotalData.class, this.serverMultiMonthSleepTotalDataDao);
        registerDao(ServerSleepDayData.class, this.serverSleepDayDataDao);
        registerDao(ServerSleepMonthData.class, this.serverSleepMonthDataDao);
        registerDao(SportCard.class, this.sportCardDao);
        registerDao(SportDistanceBean.class, this.sportDistanceBeanDao);
        registerDao(SportGpsData.class, this.sportGpsDataDao);
        registerDao(SportHealth.class, this.sportHealthDao);
        registerDao(StepDayData.class, this.stepDayDataDao);
        registerDao(TempUnitSetting.class, this.tempUnitSettingDao);
        registerDao(TimeSet.class, this.timeSetDao);
        registerDao(UnitSetting.class, this.unitSettingDao);
        registerDao(UserInfo.class, this.userInfoDao);
        registerDao(UserMedalInfo.class, this.userMedalInfoDao);
        registerDao(UserTargetNew.class, this.userTargetNewDao);
        registerDao(WalkDayData.class, this.walkDayDataDao);
        registerDao(WalkMonthTotalData.class, this.walkMonthTotalDataDao);
        registerDao(WalkYearTotalData.class, this.walkYearTotalDataDao);
        registerDao(WeekStartSetting.class, this.weekStartSettingDao);
        registerDao(WeightItemBean.class, this.weightItemBeanDao);
    }

    public void clear() {
        this.activeTimeDayDataDaoConfig.clearIdentityScope();
        this.agreementPrivacyVersionDaoConfig.clearIdentityScope();
        this.calorieDayDataDaoConfig.clearIdentityScope();
        this.dataDownLoadStateDaoConfig.clearIdentityScope();
        this.dataPullConfigInfoDaoConfig.clearIdentityScope();
        this.gpsColorsDaoConfig.clearIdentityScope();
        this.healthPressureDaoConfig.clearIdentityScope();
        this.healthVolumeDataDaoConfig.clearIdentityScope();
        this.homeCardDaoConfig.clearIdentityScope();
        this.lifeCycleItemBeanDaoConfig.clearIdentityScope();
        this.mapEngineDaoConfig.clearIdentityScope();
        this.menstruationConfigDaoConfig.clearIdentityScope();
        this.messageEntityDaoConfig.clearIdentityScope();
        this.multiDaysWalkTotalDataDaoConfig.clearIdentityScope();
        this.noticeAppEntityDaoConfig.clearIdentityScope();
        this.privateSafeSettingDaoConfig.clearIdentityScope();
        this.serverBloodOxyDayDataDaoConfig.clearIdentityScope();
        this.serverBloodOxyMonthDataDaoConfig.clearIdentityScope();
        this.serverDaysBloodOxyDataDaoConfig.clearIdentityScope();
        this.serverDaysSleepDataDaoConfig.clearIdentityScope();
        this.serverHeartRateDayDataDaoConfig.clearIdentityScope();
        this.serverMultiMonthBloodOxyTotalDataDaoConfig.clearIdentityScope();
        this.serverMultiMonthSleepTotalDataDaoConfig.clearIdentityScope();
        this.serverSleepDayDataDaoConfig.clearIdentityScope();
        this.serverSleepMonthDataDaoConfig.clearIdentityScope();
        this.sportCardDaoConfig.clearIdentityScope();
        this.sportDistanceBeanDaoConfig.clearIdentityScope();
        this.sportGpsDataDaoConfig.clearIdentityScope();
        this.sportHealthDaoConfig.clearIdentityScope();
        this.stepDayDataDaoConfig.clearIdentityScope();
        this.tempUnitSettingDaoConfig.clearIdentityScope();
        this.timeSetDaoConfig.clearIdentityScope();
        this.unitSettingDaoConfig.clearIdentityScope();
        this.userInfoDaoConfig.clearIdentityScope();
        this.userMedalInfoDaoConfig.clearIdentityScope();
        this.userTargetNewDaoConfig.clearIdentityScope();
        this.walkDayDataDaoConfig.clearIdentityScope();
        this.walkMonthTotalDataDaoConfig.clearIdentityScope();
        this.walkYearTotalDataDaoConfig.clearIdentityScope();
        this.weekStartSettingDaoConfig.clearIdentityScope();
        this.weightItemBeanDaoConfig.clearIdentityScope();
    }

    public ActiveTimeDayDataDao getActiveTimeDayDataDao() {
        return this.activeTimeDayDataDao;
    }

    public AgreementPrivacyVersionDao getAgreementPrivacyVersionDao() {
        return this.agreementPrivacyVersionDao;
    }

    public CalorieDayDataDao getCalorieDayDataDao() {
        return this.calorieDayDataDao;
    }

    public DataDownLoadStateDao getDataDownLoadStateDao() {
        return this.dataDownLoadStateDao;
    }

    public DataPullConfigInfoDao getDataPullConfigInfoDao() {
        return this.dataPullConfigInfoDao;
    }

    public GpsColorsDao getGpsColorsDao() {
        return this.gpsColorsDao;
    }

    public HealthPressureDao getHealthPressureDao() {
        return this.healthPressureDao;
    }

    public HealthVolumeDataDao getHealthVolumeDataDao() {
        return this.healthVolumeDataDao;
    }

    public HomeCardDao getHomeCardDao() {
        return this.homeCardDao;
    }

    public LifeCycleItemBeanDao getLifeCycleItemBeanDao() {
        return this.lifeCycleItemBeanDao;
    }

    public MapEngineDao getMapEngineDao() {
        return this.mapEngineDao;
    }

    public MenstruationConfigDao getMenstruationConfigDao() {
        return this.menstruationConfigDao;
    }

    public MessageEntityDao getMessageEntityDao() {
        return this.messageEntityDao;
    }

    public MultiDaysWalkTotalDataDao getMultiDaysWalkTotalDataDao() {
        return this.multiDaysWalkTotalDataDao;
    }

    public NoticeAppEntityDao getNoticeAppEntityDao() {
        return this.noticeAppEntityDao;
    }

    public PrivateSafeSettingDao getPrivateSafeSettingDao() {
        return this.privateSafeSettingDao;
    }

    public ServerBloodOxyDayDataDao getServerBloodOxyDayDataDao() {
        return this.serverBloodOxyDayDataDao;
    }

    public ServerBloodOxyMonthDataDao getServerBloodOxyMonthDataDao() {
        return this.serverBloodOxyMonthDataDao;
    }

    public ServerDaysBloodOxyDataDao getServerDaysBloodOxyDataDao() {
        return this.serverDaysBloodOxyDataDao;
    }

    public ServerDaysSleepDataDao getServerDaysSleepDataDao() {
        return this.serverDaysSleepDataDao;
    }

    public ServerHeartRateDayDataDao getServerHeartRateDayDataDao() {
        return this.serverHeartRateDayDataDao;
    }

    public ServerMultiMonthBloodOxyTotalDataDao getServerMultiMonthBloodOxyTotalDataDao() {
        return this.serverMultiMonthBloodOxyTotalDataDao;
    }

    public ServerMultiMonthSleepTotalDataDao getServerMultiMonthSleepTotalDataDao() {
        return this.serverMultiMonthSleepTotalDataDao;
    }

    public ServerSleepDayDataDao getServerSleepDayDataDao() {
        return this.serverSleepDayDataDao;
    }

    public ServerSleepMonthDataDao getServerSleepMonthDataDao() {
        return this.serverSleepMonthDataDao;
    }

    public SportCardDao getSportCardDao() {
        return this.sportCardDao;
    }

    public SportDistanceBeanDao getSportDistanceBeanDao() {
        return this.sportDistanceBeanDao;
    }

    public SportGpsDataDao getSportGpsDataDao() {
        return this.sportGpsDataDao;
    }

    public SportHealthDao getSportHealthDao() {
        return this.sportHealthDao;
    }

    public StepDayDataDao getStepDayDataDao() {
        return this.stepDayDataDao;
    }

    public TempUnitSettingDao getTempUnitSettingDao() {
        return this.tempUnitSettingDao;
    }

    public TimeSetDao getTimeSetDao() {
        return this.timeSetDao;
    }

    public UnitSettingDao getUnitSettingDao() {
        return this.unitSettingDao;
    }

    public UserInfoDao getUserInfoDao() {
        return this.userInfoDao;
    }

    public UserMedalInfoDao getUserMedalInfoDao() {
        return this.userMedalInfoDao;
    }

    public UserTargetNewDao getUserTargetNewDao() {
        return this.userTargetNewDao;
    }

    public WalkDayDataDao getWalkDayDataDao() {
        return this.walkDayDataDao;
    }

    public WalkMonthTotalDataDao getWalkMonthTotalDataDao() {
        return this.walkMonthTotalDataDao;
    }

    public WalkYearTotalDataDao getWalkYearTotalDataDao() {
        return this.walkYearTotalDataDao;
    }

    public WeekStartSettingDao getWeekStartSettingDao() {
        return this.weekStartSettingDao;
    }

    public WeightItemBeanDao getWeightItemBeanDao() {
        return this.weightItemBeanDao;
    }
}