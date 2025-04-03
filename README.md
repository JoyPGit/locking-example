# myrepo
echo "# myrepo" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/loretoparisi/myrepo.git
git push -u origin master

https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account

https://stackoverflow.com/a/58952004/5667980
Optimistic locking is a very useful technique, and it works just fine even when using 
less-strict isolation levels, like Read Committed, or when reads and writes are executed 
in subsequent database transactions.

The downside of optimistic locking is that a rollback will be triggered by the 
data access framework upon catching an OptimisticLockException, therefore losing all 
the work we've done previously by the currently executing transaction.

The more contention, the more conflicts, and the greater the chance of aborting transactions. 
Rollbacks can be costly for the database system as it needs to revert all current 
pending changes which might involve both table rows and index records.

For this reason, pessimistic locking might be more suitable when conflicts happen frequently, 
as it reduces the chance of rolling back transactions.