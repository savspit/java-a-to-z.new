package shestakov.models;

import shestakov.services.Buffer;

public class TransactionThread extends Thread{
    private final AccountStorage storage;
    private final long accountId;
    private final float sum;

    public TransactionThread(AccountStorage storage, long accountId, float sum) {
        this.storage = storage;
        this.accountId = accountId;
        this.sum = sum;
    }

    @Override
    public void run() {
        Account currentAccount = this.storage.get(this.accountId);
        synchronized (currentAccount) {
            if (this.sum > 0 || (this.sum < 0 && currentAccount.getAmount() >= -this.sum)) {
                currentAccount.setAmount(currentAccount.getAmount() + this.sum);
                } else {
                new Buffer().show(currentAccount, "error occured. please try again later");
            }
        }
    }
}
