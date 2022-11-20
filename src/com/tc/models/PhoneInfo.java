package com.tc.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PhoneInfo {

    private List<Long> blockedPhone;
    private Set<MissedCall> missedCalls;

    public PhoneInfo() {
        this.blockedPhone = new ArrayList<>();
        this.missedCalls = new HashSet<>();
    }

    public void blockedPhone(Long phone) {
        blockedPhone.add(phone);
    }

    public boolean unBlockedPhone(Long phone) {
        if (blockedPhone.contains(phone)) {
            blockedPhone.remove(phone);
            return true;
        }
        return false;
    }

    public boolean isBlocked(Long phone) {
        return blockedPhone.contains(phone);
    }

    public void addMissingPhone(Long phone) {
        MissedCall missedCall = getMissedCall(phone);
        if (missedCall == null)
            missedCall = new MissedCall(phone);
        missedCall.incrementCount();
        missedCalls.add(missedCall);
    }

    public MissedCall getMissedCall(Long phone) {
        return missedCalls.stream().filter(missedCall -> Objects.deepEquals(phone, missedCall.getPhoneNumber()))
                .findFirst().orElse(null);
    }

    public void markMissedCallSeen(Long phone) {
        MissedCall missedCall = getMissedCall(phone);
        missedCalls.remove(missedCall);
    }

    public Set<MissedCall> getMissedCalls() {
        return missedCalls;
    }
}

class MissedCall {
    private Long phoneNumber;
    private Long count;

    public MissedCall() {
        this.count = 0L;
    }

    public void incrementCount() {
        this.count = this.count + 1;
    }

    public MissedCall(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.count = 0L;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissedCall that = (MissedCall) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "MissedCall{" +
                "phoneNumber=" + phoneNumber +
                ", count=" + count +
                '}';
    }
}
