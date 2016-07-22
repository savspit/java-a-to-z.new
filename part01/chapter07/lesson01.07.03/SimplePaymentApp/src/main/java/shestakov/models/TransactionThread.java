package shestakov.models;

import shestakov.services.Buffer;

public class TransactionThread extends Thread{
    private final Account donor;
    private final Account recipient;
    private final float sum;

    public TransactionThread(Account donor, Account recipient, float sum) {
        this.donor = donor;
        this.recipient = recipient;
        this.sum = sum;
    }

    @Override
    public void run() {
        synchronized (donor) {
            synchronized (recipient) {
                if(donor.getAmount() >= this.sum) {
                    donor.setAmount(donor.getAmount() - this.sum);
                    recipient.setAmount(recipient.getAmount() + this.sum);
                } else {
                    new Buffer().show(donor, "error occured. please try again later");
                }
            }
        }
    }
}
