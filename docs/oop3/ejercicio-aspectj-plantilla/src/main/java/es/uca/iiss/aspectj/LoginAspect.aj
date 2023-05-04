public aspect LoginAspect {
    before() : (execution(public void Bank.makeTransaction()) || execution(public void Bank.takeMoneyOut())) {
        System.out.println("The login is required");
    }

    after() returning : execution(public void Bank.showUsers()) {
        System.out.println("The database is empty");
    }
}