<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Student </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                 <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="id">NIC No</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.id" id="id" class="form-control input-sm" placeholder="Enter Your NIC No" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="firstName">First Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.student.firstName" id="firstName" class="username form-control input-sm" placeholder="Enter Your First Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="lastName">Last Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.lastName" id="lastName" class="username form-control input-sm" placeholder="Enter Your Last name" required ng-minlength="3"/>
                            </div>
                        </div>
                   	</div>
 	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="age">Age</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.student.age" id="age" class="form-control input-sm" placeholder="Enter Your Age." required ng-pattern="ctrl.onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
 	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="address">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.student.address" id="address" class="form-control input-sm" placeholder="Enter your Address." required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
 	                 <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="telephone">Telephone</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.student.telephone" id="telephone" class="form-control input-sm" placeholder="Enter Your TP No." required ng-pattern="ctrl.onlyIntegers"/>
                            </div>
                        </div>
                     </div>
 	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.student.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Student </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		           <thead>
                        <tr>
                            <th>NIC NO</th>
                            <th>FIRST NAME</th>
                            <th>LAST AME</th>
                            <th>AGE</th>
                            <th>ADDRESS</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="u in ctrl.getAllStudent()">
                            <td>{{u.id}}</td>
                            <td>{{u.firstName}}</td>
                            <td>{{u.lastName}}</td>
                            <td>{{u.age}}</td>
                            <td>{{u.address}}</td>
                            <td><button type="button" ng-click="ctrl.editStudent(u.id)" class="btn btn-success custom-width">Edit</button></td>
                            <td><button type="button" ng-click="ctrl.removeStudent(u.id)" class="btn btn-danger custom-width">Remove</button></td>
                        </tr>
                    </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>