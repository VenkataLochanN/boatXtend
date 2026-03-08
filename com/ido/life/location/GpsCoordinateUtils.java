package com.ido.life.location;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GpsCoordinateUtils {
    private static final double A = 6378245.0d;
    private static final double EE = 0.006693421622965943d;
    private static final double PI = 3.141592653589793d;
    private static final String TAG = GpsCoordinateUtils.class.getSimpleName();
    private static final Point[] MAINLAND = {new Point(27.32083d, 88.91693d), new Point(27.54243d, 88.76464d), new Point(28.00805d, 88.83575d), new Point(28.1168d, 88.62435d), new Point(27.86605d, 88.14279d), new Point(27.82305d, 87.19275d), new Point(28.11166d, 86.69527d), new Point(27.90888d, 86.45137d), new Point(28.15805d, 86.19769d), new Point(27.88625d, 86.0054d), new Point(28.27916d, 85.72137d), new Point(28.30666d, 85.11095d), new Point(28.59104d, 85.19518d), new Point(28.54444d, 84.84665d), new Point(28.73402d, 84.48623d), new Point(29.26097d, 84.11651d), new Point(29.18902d, 83.5479d), new Point(29.63166d, 83.19109d), new Point(30.06923d, 82.17525d), new Point(30.33444d, 82.11123d), new Point(30.385d, 81.42623d), new Point(30.01194d, 81.23221d), new Point(30.20435d, 81.02536d), new Point(30.57552d, 80.207d), new Point(30.73374d, 80.25423d), new Point(30.96583d, 79.86304d), new Point(30.95708d, 79.55429d), new Point(31.43729d, 79.08082d), new Point(31.30895d, 78.76825d), new Point(31.96847d, 78.77075d), new Point(32.24304d, 78.47594d), new Point(32.5561d, 78.40595d), new Point(32.63902d, 78.74623d), new Point(32.35083d, 78.9711d), new Point(32.75666d, 79.52874d), new Point(33.09944d, 79.37511d), new Point(33.42863d, 78.93623d), new Point(33.52041d, 78.81387d), new Point(34.06833d, 78.73581d), new Point(34.35001d, 78.98535d), new Point(34.6118d, 78.33707d), new Point(35.28069d, 78.02305d), new Point(35.49902d, 78.0718d), new Point(35.50133d, 77.82393d), new Point(35.6125d, 76.89526d), new Point(35.90665d, 76.55304d), new Point(35.81458d, 76.18061d), new Point(36.07082d, 75.92887d), new Point(36.23751d, 76.04166d), new Point(36.66343d, 75.85984d), new Point(36.73169d, 75.45179d), new Point(36.91156d, 75.39902d), new Point(36.99719d, 75.14787d), new Point(37.02782d, 74.56543d), new Point(37.17d, 74.39089d), new Point(37.23733d, 74.91574d), new Point(37.40659d, 75.18748d), new Point(37.65243d, 74.9036d), new Point(38.47256d, 74.85442d), new Point(38.67438d, 74.35471d), new Point(38.61271d, 73.81401d), new Point(38.88653d, 73.70818d), new Point(38.97256d, 73.85235d), new Point(39.23569d, 73.62005d), new Point(39.45483d, 73.65569d), new Point(39.59965d, 73.95471d), new Point(39.76896d, 73.8429d), new Point(40.04202d, 73.99096d), new Point(40.32792d, 74.88089d), new Point(40.51723d, 74.8588d), new Point(40.45042d, 75.23394d), new Point(40.64452d, 75.58284d), new Point(40.298d, 75.70374d), new Point(40.35324d, 76.3344d), new Point(41.01258d, 76.87067d), new Point(41.04079d, 78.08083d), new Point(41.39286d, 78.39554d), new Point(42.03954d, 80.24513d), new Point(42.19622d, 80.23402d), new Point(42.63245d, 80.15804d), new Point(42.81565d, 80.25796d), new Point(42.88545d, 80.57226d), new Point(43.02906d, 80.38405d), new Point(43.1683d, 80.81526d), new Point(44.11378d, 80.36887d), new Point(44.6358d, 80.38499d), new Point(44.73408d, 80.51589d), new Point(44.90282d, 79.87106d), new Point(45.3497d, 81.67928d), new Point(45.15748d, 81.94803d), new Point(45.13303d, 82.56638d), new Point(45.43581d, 82.64624d), new Point(45.5831d, 82.32179d), new Point(47.20061d, 83.03443d), new Point(46.97332d, 83.93026d), new Point(46.99361d, 84.67804d), new Point(46.8277d, 84.80318d), new Point(47.0591d, 85.52257d), new Point(47.26221d, 85.70139d), new Point(47.93721d, 85.53707d), new Point(48.39333d, 85.76596d), new Point(48.54277d, 86.59791d), new Point(49.1102d, 86.87602d), new Point(49.09262d, 87.34821d), new Point(49.17295d, 87.8407d), new Point(48.98304d, 87.89291d), new Point(48.88103d, 87.7611d), new Point(48.73499d, 88.05942d), new Point(48.56541d, 87.99194d), new Point(48.40582d, 88.51679d), new Point(48.21193d, 88.61179d), new Point(47.99374d, 89.08514d), new Point(47.88791d, 90.07096d), new Point(46.95221d, 90.9136d), new Point(46.57735d, 91.07027d), new Point(46.29694d, 90.92151d), new Point(46.01735d, 91.02651d), new Point(45.57972d, 90.68193d), new Point(45.25305d, 90.89694d), new Point(45.07729d, 91.56088d), new Point(44.95721d, 93.5547d), new Point(44.35499d, 94.71735d), new Point(44.29416d, 95.41061d), new Point(44.01937d, 95.34109d), new Point(43.99311d, 95.53339d), new Point(43.28388d, 95.87901d), new Point(42.73499d, 96.38206d), new Point(42.79583d, 97.1654d), new Point(42.57194d, 99.51012d), new Point(42.67707d, 100.8425d), new Point(42.50972d, 101.8147d), new Point(42.23333d, 102.0772d), new Point(41.88721d, 103.4164d), new Point(41.87721d, 104.5267d), new Point(41.67068d, 104.5237d), new Point(41.58666d, 105.0065d), new Point(42.46624d, 107.4758d), new Point(42.42999d, 109.3107d), new Point(42.64576d, 110.1064d), new Point(43.31694d, 110.9897d), new Point(43.69221d, 111.9583d), new Point(44.37527d, 111.4214d), new Point(45.04944d, 111.873d), new Point(45.08055d, 112.4272d), new Point(44.8461d, 112.853d), new Point(44.74527d, 113.638d), new Point(45.38943d, 114.5453d), new Point(45.4586d, 115.7019d), new Point(45.72193d, 116.2104d), new Point(46.29583d, 116.5855d), new Point(46.41888d, 117.3755d), new Point(46.57069d, 117.425d), new Point(46.53645d, 117.8455d), new Point(46.73638d, 118.3147d), new Point(46.59895d, 119.7068d), new Point(46.71513d, 119.9315d), new Point(46.90221d, 119.9225d), new Point(47.66499d, 119.125d), new Point(47.99475d, 118.5393d), new Point(48.01125d, 117.8046d), new Point(47.65741d, 117.3827d), new Point(47.88805d, 116.8747d), new Point(47.87819d, 116.2624d), new Point(47.69186d, 115.9231d), new Point(47.91749d, 115.5944d), new Point(48.14353d, 115.5491d), new Point(48.25249d, 115.8358d), new Point(48.52055d, 115.8111d), new Point(49.83047d, 116.7114d), new Point(49.52058d, 117.8747d), new Point(49.92263d, 118.5746d), new Point(50.09631d, 119.321d), new Point(50.33028d, 119.36d), new Point(50.39027d, 119.1386d), new Point(51.62083d, 120.0641d), new Point(52.115d, 120.7767d), new Point(52.34423d, 120.6259d), new Point(52.54267d, 120.7122d), new Point(52.58805d, 120.0819d), new Point(52.76819d, 120.0314d), new Point(53.26374d, 120.8307d), new Point(53.54361d, 123.6147d), new Point(53.18832d, 124.4933d), new Point(53.05027d, 125.62d), new Point(52.8752d, 125.6573d), new Point(52.75722d, 126.0968d), new Point(52.5761d, 125.9943d), new Point(52.12694d, 126.555d), new Point(51.99437d, 126.4412d), new Point(51.38138d, 126.9139d), new Point(51.26555d, 126.8176d), new Point(51.31923d, 126.9689d), new Point(51.05825d, 126.9331d), new Point(50.74138d, 127.2919d), new Point(50.31472d, 127.334d), new Point(50.20856d, 127.5861d), new Point(49.80588d, 127.515d), new Point(49.58665d, 127.838d), new Point(49.58443d, 128.7119d), new Point(49.34676d, 129.1118d), new Point(49.4158d, 129.4902d), new Point(48.86464d, 130.2246d), new Point(48.86041d, 130.674d), new Point(48.60576d, 130.5236d), new Point(48.3268d, 130.824d), new Point(48.10839d, 130.6598d), new Point(47.68721d, 130.9922d), new Point(47.71027d, 132.5211d), new Point(48.09888d, 133.0827d), new Point(48.06888d, 133.4843d), new Point(48.39112d, 134.4153d), new Point(48.26713d, 134.7408d), new Point(47.99207d, 134.5576d), new Point(47.70027d, 134.7608d), new Point(47.32333d, 134.1825d), new Point(46.64017d, 133.9977d), new Point(46.47888d, 133.8472d), new Point(46.25363d, 133.9016d), new Point(45.82347d, 133.4761d), new Point(45.62458d, 133.4702d), new Point(45.45083d, 133.1491d), new Point(45.05694d, 133.0253d), new Point(45.34582d, 131.8684d), new Point(44.97388d, 131.4691d), new Point(44.83649d, 130.953d), new Point(44.05193d, 131.298d), new Point(43.53624d, 131.1912d), new Point(43.38958d, 131.3104d), new Point(42.91645d, 131.1285d), new Point(42.74485d, 130.4327d), new Point(42.42186d, 130.6044d), new Point(42.71416d, 130.2468d), new Point(42.88794d, 130.2514d), new Point(43.00457d, 129.9046d), new Point(42.43582d, 129.6955d), new Point(42.44624d, 129.3493d), new Point(42.02736d, 128.9269d), new Point(42.00124d, 128.0566d), new Point(41.58284d, 128.3002d), new Point(41.38124d, 128.1529d), new Point(41.47249d, 127.2708d), new Point(41.79222d, 126.9047d), new Point(41.61176d, 126.5661d), new Point(40.89694d, 126.0118d), new Point(40.47037d, 124.8851d), new Point(40.09362d, 124.3736d), new Point(39.82777d, 124.128d), new Point(39.8143d, 123.2422d), new Point(39.67388d, 123.2167d), new Point(38.99638d, 121.648d), new Point(38.8611d, 121.6982d), new Point(38.71909d, 121.1873d), new Point(38.91221d, 121.0887d), new Point(39.09013d, 121.6794d), new Point(39.2186d, 121.5994d), new Point(39.35166d, 121.7511d), new Point(39.52847d, 121.2283d), new Point(39.62322d, 121.533d), new Point(39.81138d, 121.4683d), new Point(40.00305d, 121.881d), new Point(40.50562d, 122.2987d), new Point(40.73874d, 122.0521d), new Point(40.92194d, 121.1775d), new Point(40.1961d, 120.4468d), new Point(39.87242d, 119.5264d), new Point(39.15693d, 118.9715d), new Point(39.04083d, 118.3273d), new Point(39.19846d, 117.889d), new Point(38.67555d, 117.5364d), new Point(38.38666d, 117.6722d), new Point(38.16721d, 118.0281d), new Point(38.1529d, 118.8378d), new Point(37.87832d, 119.0355d), new Point(37.30054d, 118.9566d), new Point(37.14361d, 119.2328d), new Point(37.15138d, 119.7672d), new Point(37.35228d, 119.8529d), new Point(37.83499d, 120.7371d), new Point(37.42458d, 121.58d), new Point(37.55256d, 122.1282d), new Point(37.41833d, 122.1814d), new Point(37.39624d, 122.5586d), new Point(37.20999d, 122.5972d), new Point(37.02583d, 122.4005d), new Point(37.01978d, 122.5392d), new Point(36.89361d, 122.5047d), new Point(36.84298d, 122.1923d), new Point(37.00027d, 121.9566d), new Point(36.75889d, 121.5944d), new Point(36.61666d, 120.7764d), new Point(36.52638d, 120.96d), new Point(36.37582d, 120.8753d), new Point(36.42277d, 120.7062d), new Point(36.14075d, 120.6956d), new Point(36.0419d, 120.3436d), new Point(36.26345d, 120.3078d), new Point(36.19998d, 120.0889d), new Point(35.95943d, 120.2378d), new Point(35.57893d, 119.6475d), new Point(34.88499d, 119.1761d), new Point(34.31145d, 120.2487d), new Point(32.97499d, 120.8858d), new Point(32.63889d, 120.8375d), new Point(32.42958d, 121.3348d), new Point(32.11333d, 121.4412d), new Point(32.02166d, 121.7066d), new Point(31.67833d, 121.8275d), new Point(31.86639d, 120.9444d), new Point(32.09361d, 120.6019d), new Point(31.94555d, 120.099d), new Point(32.30638d, 119.8267d), new Point(32.26277d, 119.6317d), new Point(31.90388d, 120.1364d), new Point(31.98833d, 120.7026d), new Point(31.81944d, 120.7196d), new Point(31.30889d, 121.6681d), new Point(30.97986d, 121.8828d), new Point(30.85305d, 121.8469d), new Point(30.56889d, 120.9915d), new Point(30.33555d, 120.8144d), new Point(30.39298d, 120.4586d), new Point(30.19694d, 120.15d), new Point(30.31027d, 120.5082d), new Point(30.06465d, 120.7916d), new Point(30.30458d, 121.2808d), new Point(29.96305d, 121.6778d), new Point(29.88211d, 122.1196d), new Point(29.51167d, 121.4483d), new Point(29.58916d, 121.9744d), new Point(29.19527d, 121.9336d), new Point(29.18388d, 121.8119d), new Point(29.37236d, 121.7969d), new Point(29.19729d, 121.7444d), new Point(29.29111d, 121.5611d), new Point(29.1634d, 121.4135d), new Point(29.02194d, 121.6914d), new Point(28.9359d, 121.4908d), new Point(28.72798d, 121.6113d), new Point(28.84215d, 121.1464d), new Point(28.66993d, 121.4844d), new Point(28.34722d, 121.6417d), new Point(28.13889d, 121.3419d), new Point(28.38277d, 121.1651d), new Point(27.98222d, 120.9353d), new Point(28.07944d, 120.5908d), new Point(27.87229d, 120.84d), new Point(27.59319d, 120.5812d), new Point(27.45083d, 120.6655d), new Point(27.20777d, 120.5075d), new Point(27.28278d, 120.1896d), new Point(27.14764d, 120.4211d), new Point(26.89805d, 120.0332d), new Point(26.64465d, 120.128d), new Point(26.51778d, 119.8603d), new Point(26.78823d, 120.0733d), new Point(26.64888d, 119.8668d), new Point(26.79611d, 119.7879d), new Point(26.75625d, 119.5503d), new Point(26.44222d, 119.8204d), new Point(26.47388d, 119.5775d), new Point(26.33861d, 119.658d), new Point(26.36777d, 119.9489d), new Point(25.99694d, 119.4253d), new Point(26.14041d, 119.0975d), new Point(25.93788d, 119.354d), new Point(25.99069d, 119.7058d), new Point(25.67996d, 119.5807d), new Point(25.68222d, 119.4522d), new Point(25.35333d, 119.6454d), new Point(25.60649d, 119.3149d), new Point(25.42097d, 119.1053d), new Point(25.25319d, 119.3526d), new Point(25.17208d, 119.2726d), new Point(25.2426d, 118.8749d), new Point(24.97194d, 118.9866d), new Point(24.88291d, 118.5729d), new Point(24.75673d, 118.7631d), new Point(24.52861d, 118.5953d), new Point(24.53638d, 118.2397d), new Point(24.68194d, 118.1688d), new Point(24.44024d, 118.0199d), new Point(24.46019d, 117.7947d), new Point(24.25875d, 118.1237d), new Point(23.62437d, 117.1957d), new Point(23.65919d, 116.9179d), new Point(23.355d, 116.7603d), new Point(23.42024d, 116.5322d), new Point(23.23666d, 116.7871d), new Point(23.21083d, 116.5139d), new Point(22.93902d, 116.4817d), new Point(22.73916d, 115.7978d), new Point(22.88416d, 115.6403d), new Point(22.65889d, 115.5367d), new Point(22.80833d, 115.1614d), new Point(22.70277d, 114.8889d), new Point(22.53305d, 114.8722d), new Point(22.64027d, 114.718d), new Point(22.81402d, 114.7782d), new Point(22.69972d, 114.5208d), new Point(22.50423d, 114.6136d), new Point(22.55004d, 114.2223d), new Point(22.42993d, 114.3885d), new Point(22.26056d, 114.2961d), new Point(22.36736d, 113.9056d), new Point(22.50874d, 114.0337d), new Point(22.47444d, 113.8608d), new Point(22.83458d, 113.606d), new Point(23.05027d, 113.5253d), new Point(23.11724d, 113.8219d), new Point(23.05083d, 113.4793d), new Point(22.87986d, 113.3629d), new Point(22.54944d, 113.5648d), new Point(22.18701d, 113.5527d), new Point(22.56701d, 113.1687d), new Point(22.17965d, 113.3868d), new Point(22.04069d, 113.2226d), new Point(22.20485d, 113.0848d), new Point(21.8693d, 112.94d), new Point(21.96472d, 112.824d), new Point(21.70139d, 112.2819d), new Point(21.91611d, 111.8921d), new Point(21.75139d, 111.9669d), new Point(21.77819d, 111.6762d), new Point(21.61264d, 111.7832d), new Point(21.5268d, 111.644d), new Point(21.52528d, 111.0285d), new Point(21.21138d, 110.5328d), new Point(21.37322d, 110.3944d), new Point(20.84381d, 110.1594d), new Point(20.84083d, 110.3755d), new Point(20.64d, 110.3239d), new Point(20.48618d, 110.5274d), new Point(20.24611d, 110.2789d), new Point(20.2336d, 109.9244d), new Point(20.4318d, 110.0069d), new Point(20.92416d, 109.6629d), new Point(21.44694d, 109.9411d), new Point(21.50569d, 109.6605d), new Point(21.72333d, 109.5733d), new Point(21.49499d, 109.5344d), new Point(21.39666d, 109.1428d), new Point(21.58305d, 109.1375d), new Point(21.61611d, 108.911d), new Point(21.79889d, 108.8702d), new Point(21.59888d, 108.7403d), new Point(21.93562d, 108.4692d), new Point(21.59014d, 108.5125d), new Point(21.68999d, 108.3336d), new Point(21.51444d, 108.2447d), new Point(21.54241d, 107.99d), new Point(21.66694d, 107.7831d), new Point(21.60526d, 107.3627d), new Point(22.03083d, 106.6933d), new Point(22.45682d, 106.5517d), new Point(22.76389d, 106.7875d), new Point(22.86694d, 106.7029d), new Point(22.91253d, 105.8771d), new Point(23.32416d, 105.3587d), new Point(23.18027d, 104.9075d), new Point(22.81805d, 104.7319d), new Point(22.6875d, 104.3747d), new Point(22.79812d, 104.1113d), new Point(22.50387d, 103.9687d), new Point(22.78287d, 103.6538d), new Point(22.58436d, 103.5224d), new Point(22.79451d, 103.3337d), new Point(22.43652d, 103.0304d), new Point(22.77187d, 102.4744d), new Point(22.39629d, 102.1407d), new Point(22.49777d, 101.7415d), new Point(22.20916d, 101.5744d), new Point(21.83444d, 101.7653d), new Point(21.14451d, 101.786d), new Point(21.17687d, 101.2919d), new Point(21.57264d, 101.1482d), new Point(21.76903d, 101.099d), new Point(21.47694d, 100.6397d), new Point(21.43546d, 100.2057d), new Point(21.72555d, 99.97763d), new Point(22.05018d, 99.95741d), new Point(22.15592d, 99.16785d), new Point(22.93659d, 99.56484d), new Point(23.08204d, 99.5113d), new Point(23.18916d, 98.92747d), new Point(23.97076d, 98.67991d), new Point(24.16007d, 98.89073d), new Point(23.92999d, 97.54762d), new Point(24.26055d, 97.7593d), new Point(24.47666d, 97.54305d), new Point(24.73992d, 97.55255d), new Point(25.61527d, 98.19109d), new Point(25.56944d, 98.36137d), new Point(25.85597d, 98.7104d), new Point(26.12527d, 98.56944d), new Point(26.18472d, 98.73109d), new Point(26.79166d, 98.77777d), new Point(27.52972d, 98.69699d), new Point(27.6725d, 98.45888d), new Point(27.54014d, 98.31992d), new Point(28.14889d, 98.14499d), new Point(28.54652d, 97.55887d), new Point(28.22277d, 97.34888d), new Point(28.46749d, 96.65387d), new Point(28.35111d, 96.40193d), new Point(28.525d, 96.34027d), new Point(28.79569d, 96.61373d), new Point(29.05666d, 96.47083d), new Point(28.90138d, 96.17532d), new Point(29.05972d, 96.14888d), new Point(29.25757d, 96.39172d), new Point(29.46444d, 96.08315d), new Point(29.03527d, 95.38777d), new Point(29.33346d, 94.64751d), new Point(29.07348d, 94.23456d), new Point(28.6692d, 93.96172d), new Point(28.61876d, 93.35194d), new Point(28.3193d, 93.22205d), new Point(28.1419d, 92.71044d), new Point(27.86194d, 92.54498d), new Point(27.76472d, 91.65776d), new Point(27.945d, 91.66277d), new Point(28.08111d, 91.30138d), new Point(27.96999d, 91.08693d), new Point(28.07958d, 90.3765d), new Point(28.24257d, 90.38898d), new Point(28.32369d, 89.99819d), new Point(28.05777d, 89.48749d), new Point(27.32083d, 88.91693d)};
    private static final Point[] TAIWAN = {new Point(25.13474d, 121.4441d), new Point(25.28361d, 121.5632d), new Point(25.00722d, 122.0004d), new Point(24.85028d, 121.8182d), new Point(24.47638d, 121.8397d), new Point(23.0875d, 121.3556d), new Point(21.92791d, 120.7196d), new Point(22.31277d, 120.6103d), new Point(22.54044d, 120.3071d), new Point(23.04437d, 120.0539d), new Point(23.61708d, 120.1112d), new Point(25.00166d, 121.0017d), new Point(25.13474d, 121.4441d)};
    private static final Point[] HAINAN = {new Point(19.52888d, 110.855d), new Point(19.16761d, 110.4832d), new Point(18.80083d, 110.5255d), new Point(18.3852d, 110.0503d), new Point(18.39152d, 109.7594d), new Point(18.19777d, 109.7036d), new Point(18.50562d, 108.6871d), new Point(19.28028d, 108.6283d), new Point(19.76d, 109.2939d), new Point(19.7236d, 109.1653d), new Point(19.89972d, 109.2572d), new Point(19.82861d, 109.4658d), new Point(19.99389d, 109.6108d), new Point(20.13361d, 110.6655d), new Point(19.97861d, 110.9425d), new Point(19.63829d, 111.0215d), new Point(19.52888d, 110.855d)};
    private static final Point[] CHONGMING = {new Point(31.80054d, 121.2039d), new Point(31.49972d, 121.8736d), new Point(31.53111d, 121.5464d), new Point(31.80054d, 121.2039d)};
    private static final List<Point[]> CHINA_POLYGON = new ArrayList();

    static {
        CHINA_POLYGON.add(MAINLAND);
        CHINA_POLYGON.add(TAIWAN);
        CHINA_POLYGON.add(HAINAN);
        CHINA_POLYGON.add(CHONGMING);
    }

    public static double[] calWGS84toGCJ02(double d2, double d3) {
        Point pointCalDev = calDev(d2, d3);
        double latitude = d2 + pointCalDev.getLatitude();
        double longitude = d3 + pointCalDev.getLongitude();
        CommonLogUtil.d("calWGS84toGCJ02 retLat:" + latitude + ",retLon:" + longitude);
        return new double[]{latitude, longitude};
    }

    public static double[] calWGS84toBD09(double d2, double d3) {
        Point pointCalDev = calDev(d2, d3);
        return calGCJ02toBD09(d2 + pointCalDev.getLatitude(), d3 + pointCalDev.getLongitude());
    }

    public static double[] calGCJ02toWGS84(double d2, double d3) {
        Point pointCalDev = calDev(d2, d3);
        Point pointCalDev2 = calDev(d2 - pointCalDev.getLatitude(), d3 - pointCalDev.getLongitude());
        return new double[]{d2 - pointCalDev2.getLatitude(), d3 - pointCalDev2.getLongitude()};
    }

    public static double[] calBD09toWGS84(double d2, double d3) {
        double[] dArrCalBD09toGCJ02 = calBD09toGCJ02(d2, d3);
        return calGCJ02toWGS84(dArrCalBD09toGCJ02[0], dArrCalBD09toGCJ02[1]);
    }

    private static Point calDev(double d2, double d3) {
        if (isOutOfChina(d2, d3, false)) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLocationInfoPath(), TAG, "isOutOfChina");
            return new Point(0.0d, 0.0d);
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLocationInfoPath(), TAG, "isInOfChina tanslant1 " + d2 + ",longitude:" + d3);
        double d4 = d3 - 105.0d;
        double d5 = d2 - 35.0d;
        double dCalLat = calLat(d4, d5);
        double dCalLon = calLon(d4, d5);
        double d6 = (d2 / 180.0d) * 3.141592653589793d;
        double dSin = Math.sin(d6);
        double d7 = 1.0d - ((EE * dSin) * dSin);
        double dSqrt = Math.sqrt(d7);
        double d8 = (dCalLat * 180.0d) / ((6335552.717000426d / (d7 * dSqrt)) * 3.141592653589793d);
        double dCos = (dCalLon * 180.0d) / (((A / dSqrt) * Math.cos(d6)) * 3.141592653589793d);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLocationInfoPath(), TAG, "isInOfChina tanslant2 " + d8 + ",longitude:" + dCos);
        return new Point(d8, dCos);
    }

    private static double calLat(double d2, double d3) {
        double d4 = d2 * 2.0d;
        double dSqrt = (-100.0d) + d4 + (d3 * 3.0d) + (d3 * 0.2d * d3) + (0.1d * d2 * d3) + (Math.sqrt(Math.abs(d2)) * 0.2d) + ((((Math.sin((6.0d * d2) * 3.141592653589793d) * 20.0d) + (Math.sin(d4 * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d);
        double d5 = d3 * 3.141592653589793d;
        return dSqrt + ((((Math.sin(d5) * 20.0d) + (Math.sin((d3 / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d3 / 12.0d) * 3.141592653589793d) * 160.0d) + (Math.sin(d5 / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    private static double calLon(double d2, double d3) {
        double d4 = d2 * 0.1d;
        return d2 + 300.0d + (d3 * 2.0d) + (d4 * d2) + (d4 * d3) + (Math.sqrt(Math.abs(d2)) * 0.1d) + ((((Math.sin((6.0d * d2) * 3.141592653589793d) * 20.0d) + (Math.sin((d2 * 2.0d) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d2 * 3.141592653589793d) * 20.0d) + (Math.sin((d2 / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * 3.141592653589793d) * 150.0d) + (Math.sin((d2 / 30.0d) * 3.141592653589793d) * 300.0d)) * 2.0d) / 3.0d);
    }

    public static double[] calGCJ02toBD09(double d2, double d3) {
        double dSqrt = Math.sqrt((d3 * d3) + (d2 * d2)) + (Math.sin(d2 * 3.141592653589793d) * 2.0E-5d);
        double dAtan2 = Math.atan2(d2, d3) + (Math.cos(d3 * 3.141592653589793d) * 3.0E-6d);
        return new double[]{(Math.sin(dAtan2) * dSqrt) + 0.006d, (dSqrt * Math.cos(dAtan2)) + 0.0065d};
    }

    public static double[] calBD09toGCJ02(double d2, double d3) {
        double d4 = d3 - 0.0065d;
        double d5 = d2 - 0.006d;
        double dSqrt = Math.sqrt((d4 * d4) + (d5 * d5)) - (Math.sin(d5 * 3.141592653589793d) * 2.0E-5d);
        double dAtan2 = Math.atan2(d5, d4) - (Math.cos(d4 * 3.141592653589793d) * 3.0E-6d);
        return new double[]{Math.sin(dAtan2) * dSqrt, dSqrt * Math.cos(dAtan2)};
    }

    public static boolean isOutOfChina(double d2, double d3, boolean z) {
        boolean zPointInPolygon = false;
        if (z) {
            Iterator<Point[]> it = CHINA_POLYGON.iterator();
            while (it.hasNext() && !(zPointInPolygon = pointInPolygon(it.next(), d2, d3))) {
            }
            return zPointInPolygon;
        }
        if (d3 < 72.004d || d3 > 137.8347d) {
            return true;
        }
        return d2 < 0.8293d || d2 > 55.8271d;
    }

    private static boolean pointInPolygon(Point[] pointArr, double d2, double d3) {
        boolean z = false;
        int length = pointArr.length - 1;
        for (int i = 0; i < pointArr.length; i++) {
            if (((pointArr[i].getLatitude() < d2 && pointArr[length].getLatitude() >= d2) || (pointArr[length].getLatitude() < d2 && pointArr[i].getLatitude() >= d2)) && ((pointArr[i].getLongitude() <= d3 || pointArr[length].getLongitude() <= d3) && pointArr[i].getLongitude() + (((d2 - pointArr[i].getLatitude()) / (pointArr[length].getLatitude() - pointArr[i].getLatitude())) * (pointArr[length].getLongitude() - pointArr[i].getLongitude())) < d3)) {
                z = !z;
            }
            length = i;
        }
        return z;
    }

    static class Point {
        private double latitude;
        private double longitude;

        Point(double d2, double d3) {
            this.longitude = d3;
            this.latitude = d2;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public void setLongitude(double d2) {
            this.longitude = d2;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public void setLatitude(double d2) {
            this.latitude = d2;
        }

        public String toString() {
            return this.longitude + AppInfo.DELIM + this.latitude;
        }
    }
}