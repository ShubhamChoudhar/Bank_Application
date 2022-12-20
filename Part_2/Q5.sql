SELECT B.name, COUNT(L.loanId) AS Loans, SUM(L.loanAmount) AS LoanAmount, LoanPaid
FROM Branches B, Loans L,
(SELECT L.loanId, SUM(P.paymentAmount) AS LoanPaid
FROM Loans L, Payments P
WHERE L.loanId = P.loanId
GROUP BY L.loanId) PA
WHERE L.branchName = B.name AND L.loanId = PA.loanId
GROUP BY
    B.name, LoanPaid
ORDER BY
    B.name;