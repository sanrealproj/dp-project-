<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">


	<c:if test="${ not empty message}">
		<div class="row">
			<div class="col-xs-12 col-md-offset-2 col-md-8">
				<div class="alert alert-info fade in">${message}</div>
			</div>
		</div>
	</c:if>


	<div class="row">

		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>

				</div>
				<div class="panel-body">
					<!-- Form Elements -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter
								Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" class="form-control"
									placeholder="Product Name" id="name" />
								<sf:errors cssClass="help-block" path="name" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter
								Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" class="form-control"
									placeholder="Brand Name" id="brand" />
								<sf:errors cssClass="help-block" path="brand" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product
								Description:</label>
							<div class="col-md-8">
								<sf:textarea path="description" class="form-control" rows="4"
									placeholder="Write Product Description" id="description"></sf:textarea>
								<sf:errors cssClass="help-block" path="description" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit
								Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" class="form-control"
									placeholder="Unit Price" id="unitPrice" />
								<sf:errors cssClass="help-block" path="unitPrice" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity
								Available</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" class="form-control"
									placeholder="Quantity Available" id="quantity" />

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">Upload a file</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select
								Category</label>
							<div class="col-md-8">
								<sf:select path="categoryId" items="${categories}"
									itemLabel="name" itemValue="id" class="form-control" />				
							</div>
							<c:if test ="${product.id ==0 }">
							<div class="text-right">
							
							<button type="button" class="btn btn-warning btn-xs"
								data-toggle="modal" data-target="#myCategoryModal">Add
								New Category</button>
							
								</div>
								</c:if>
						</div>

						<div class="text-right">
							<br />
							<sf:hidden path="id" />
							<sf:hidden path="code" />
							<sf:hidden path="supplierId" />
							<sf:hidden path="active" />
							
								
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" value="Save"
									class="btn btn-primary" />
							</div>
						</div>
					</sf:form>


				</div>

			</div>

		</div>

	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">New Category</h4>
	      </div>
	      <div class="modal-body">
	        
	        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">
	        	
       			<div class="form-group">
					<label class="control-label col-md-4">Name</label>
					<div class="col-md-8 validate">
						<sf:input type="text" path="name" class="form-control"
							placeholder="Category Name" /> 
					</div>
				</div>
       			
       			<div class="form-group">				
					<label class="control-label col-md-4">Description</label>
					<div class="col-md-8 validate">
						<sf:textarea path="description" class="form-control"
							placeholder="Enter category description here!" /> 
					</div>
				</div>	        	        
	        
	        
				<div class="form-group">				
					<div class="col-md-offset-4 col-md-4">					
						<input type="submit" name="submit" value="Save" class="btn btn-primary"/>						
					</div>
				</div>	        
	        </sf:form>
	      </div>
	    </div>
	  </div>
	</div>
	<hr />
	<h1>Available Products</h1>
	<hr />

	<div class="row">


		<div class='col-xs-12'>


			<table id="productsTable"
				class="table table-condensed table-bordered">

				<thead>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>
						<th>Edit</th>
					</tr>
				</thead>
				
				<tfoot>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>
						<th>Edit</th>
					</tr>
				</tfoot>


			</table>


		</div>


	</div>

</div>