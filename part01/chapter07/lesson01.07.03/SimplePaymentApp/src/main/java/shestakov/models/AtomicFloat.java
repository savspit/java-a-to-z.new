package shestakov.models;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Float.floatToIntBits;
import static java.lang.Float.intBitsToFloat;

public class AtomicFloat extends Number{
    private AtomicInteger ai;

    public AtomicFloat() {
        this(0f);
    }

    public AtomicFloat(float initialValue) {
        ai = new AtomicInteger(floatToIntBits(initialValue));
    }

    public float get() {
        return intBitsToFloat(this.ai.get());
    }

    public final void set(float value) {
        ai.set(floatToIntBits(value));
    }

    public final boolean compareAndSet(float expected, float toUpdate) {
        return ai.compareAndSet(floatToIntBits(expected), floatToIntBits(toUpdate));
    }

    public final boolean incrementBy(float sum) {
        float temp = this.ai.floatValue();
        return compareAndSet(floatToIntBits(temp), floatToIntBits(temp+sum));
    }

    public final boolean dencrementBy(float sum) {
        float temp = this.ai.floatValue();
        return ai.compareAndSet(floatToIntBits(temp), floatToIntBits(temp-sum));
    }

    public int intValue() {
        return this.ai.get();
    }

    public long longValue() {
        return this.ai.longValue();
    }

    public float floatValue() {
        return this.ai.floatValue();
    }

    public double doubleValue() {
        return this.ai.doubleValue();
    }
}
