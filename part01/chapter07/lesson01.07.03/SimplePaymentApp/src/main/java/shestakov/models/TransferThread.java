package shestakov.models;

import shestakov.services.Buffer;

public class TransferThread extends Thread{
    protected final User donor;
    protected final User recipient;
    final float sum;

    public TransferThread(User donor, User recipient, float sum) {
        this.donor = donor;
        this.recipient = recipient;
        this.sum = sum;
    }

    @Override
    public void run() {
        synchronized (this.donor) {
            synchronized (this.recipient) {
                if (this.donor.getAmount() < this.sum) {
                    new Buffer().show(this.donor, "not enouth money");
                    return;
                }
                this.donor.setAmount(this.donor.getAmount()-sum);
                this.recipient.setAmount(this.recipient.getAmount()+sum);
            }
        }
    }
}
