Sample REST API

http://dummy.restapiexample.com/


#	Route	Method	Type	Full route	Description	Details
1	/employee	GET	JSON	http://dummy.restapiexample.com/api/v1/employees	Get all employee data	Details
2	/employee/{id}	GET	JSON	http://dummy.restapiexample.com/api/v1/employee/1	Get a single employee data	Details
3	/create	POST	JSON	http://dummy.restapiexample.com/api/v1/create	Create new record in database	Details
4	/update/{id}	PUT	JSON	http://dummy.restapiexample.com/api/v1/update/21	Update an employee record	Details
5	/delete/{id}	DELETE	JSON	http://dummy.restapiexample.com/api/v1/delete/2	Delete an employee record	Details



## Flux vs Mono

Flux is equivalent to RxJava Observable is capable of emitting
- zero or more item (streams of many elements)
- and then OPTIONALLY , completing OR failing

Mono can only emit one item at the most (streams one element)

Relations:

If you concatente two Monos you will get a Flux
You can call single() on Flux to return a Mono