package prv.zielony.transaction.analyst.modern

import dao._
import prv.zielony.transaction.analyst.modern.model.CashOperationType

/**
 * Created by zielony on 28.02.16.
 */
package object services {

  /*
  private[rest] val calculateClientsAgeMedian = ClientsService.calclulateClientsAgeMedian(dao.findAllUsers _)_

  private[rest] val calculateAverageClientAge = ClientsService.calculateAverageClientAge(dao.findAllUsers _)_

  private[rest] val calculateCapitalVarianceInPeriod =
    CapitalService.calculateCapitalVarianceInPeriod(dao.findCashOperationsInPeriod)_

  private[rest] val calculateInsertionAverageInPeriod = CapitalService.calculateCashOperationsAverageInPeriod(
    dao.findCashOperationsInPeriod)(CashOperationType.Insertion, _, _)

  private[rest] val calculateWithdrawalAverageInPeriod = CapitalService.calculateCashOperationsAverageInPeriod(
    dao.findCashOperationsInPeriod)(CashOperationType.Withdrawal, _, _)

  private[rest] val countAccountsByCountry = AccountsService.countAllAccountsByCountry _

  private[rest] val calculateAverageAccountBalance =
    AccountsService.calculateAverageBalanceByCountry _
  */

  private[rest] val accountsService = AccountsService;

  private[rest] val capitalService = CapitalService;

  private[rest] val clientsService = ClientsService;
}
