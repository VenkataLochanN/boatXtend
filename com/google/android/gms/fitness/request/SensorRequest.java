package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final long zzec;
    private final int zzed;
    private final long zzhq;
    private final long zzhr;
    private final long zzhv;
    private final DataType zzq;
    private final DataSource zzr;

    public static class Builder {
        private DataType zzq;
        private DataSource zzr;
        private long zzec = -1;
        private long zzhr = 0;
        private long zzhq = 0;
        private boolean zzhw = false;
        private int zzed = 2;
        private long zzhv = LongCompanionObject.MAX_VALUE;

        public SensorRequest build() {
            DataSource dataSource;
            Preconditions.checkState((this.zzr == null && this.zzq == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzq;
            Preconditions.checkState(dataType == null || (dataSource = this.zzr) == null || dataType.equals(dataSource.getDataType()), "Specified data type is incompatible with specified data source");
            return new SensorRequest(this);
        }

        public Builder setAccuracyMode(int i) {
            if (i != 1 && i != 3) {
                i = 2;
            }
            this.zzed = i;
            return this;
        }

        public Builder setDataSource(DataSource dataSource) {
            this.zzr = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public Builder setFastestRate(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzhw = true;
            this.zzhr = timeUnit.toMicros(i);
            return this;
        }

        public Builder setMaxDeliveryLatency(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zzhq = timeUnit.toMicros(i);
            return this;
        }

        public Builder setSamplingRate(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            this.zzec = timeUnit.toMicros(j);
            if (!this.zzhw) {
                this.zzhr = this.zzec / 2;
            }
            return this;
        }

        public Builder setTimeout(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            Preconditions.checkArgument(timeUnit != null, "Invalid time unit specified");
            this.zzhv = timeUnit.toMicros(j);
            return this;
        }
    }

    private SensorRequest(DataSource dataSource, LocationRequest locationRequest) {
        this.zzec = TimeUnit.MILLISECONDS.toMicros(locationRequest.getInterval());
        this.zzhr = TimeUnit.MILLISECONDS.toMicros(locationRequest.getFastestInterval());
        this.zzhq = this.zzec;
        this.zzq = dataSource.getDataType();
        int priority = locationRequest.getPriority();
        this.zzed = priority != 100 ? priority != 104 ? 2 : 1 : 3;
        this.zzr = dataSource;
        long expirationTime = locationRequest.getExpirationTime();
        if (expirationTime == LongCompanionObject.MAX_VALUE) {
            this.zzhv = LongCompanionObject.MAX_VALUE;
        } else {
            this.zzhv = TimeUnit.MILLISECONDS.toMicros(expirationTime - SystemClock.elapsedRealtime());
        }
    }

    private SensorRequest(Builder builder) {
        this.zzr = builder.zzr;
        this.zzq = builder.zzq;
        this.zzec = builder.zzec;
        this.zzhr = builder.zzhr;
        this.zzhq = builder.zzhq;
        this.zzed = builder.zzed;
        this.zzhv = builder.zzhv;
    }

    @Deprecated
    public static SensorRequest fromLocationRequest(DataSource dataSource, LocationRequest locationRequest) {
        return new SensorRequest(dataSource, locationRequest);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SensorRequest) {
                SensorRequest sensorRequest = (SensorRequest) obj;
                if (Objects.equal(this.zzr, sensorRequest.zzr) && Objects.equal(this.zzq, sensorRequest.zzq) && this.zzec == sensorRequest.zzec && this.zzhr == sensorRequest.zzhr && this.zzhq == sensorRequest.zzhq && this.zzed == sensorRequest.zzed && this.zzhv == sensorRequest.zzhv) {
                }
            }
            return false;
        }
        return true;
    }

    public int getAccuracyMode() {
        return this.zzed;
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public long getFastestRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhr, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhq, TimeUnit.MICROSECONDS);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzec, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, Long.valueOf(this.zzec), Long.valueOf(this.zzhr), Long.valueOf(this.zzhq), Integer.valueOf(this.zzed), Long.valueOf(this.zzhv));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zzr).add("dataType", this.zzq).add("samplingRateMicros", Long.valueOf(this.zzec)).add("deliveryLatencyMicros", Long.valueOf(this.zzhq)).add("timeOutMicros", Long.valueOf(this.zzhv)).toString();
    }

    public final long zzx() {
        return this.zzhv;
    }
}