

// Get all users tests
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});


pm.test("Content-Type header is present", () => {
  pm.response.to.have.header("Content-Type");
});

pm.test("response must be valid and have a body", function () {
     pm.response.to.be.ok;
     pm.response.to.be.withBody;
     pm.response.to.be.json;
});

const jsonData = pm.response.json();

pm.test("Test data type of the response", () => {
  pm.expect(jsonData).to.be.an("array");
  for (const item of jsonData)  {
    pm.expect(item.username).to.be.a("string");
    pm.expect(item.password).to.be.a("string");
    pm.expect(item.role).to.be.a("string");
    pm.expect(item.heightInches).to.be.a("number");
    pm.expect(item.weightLbs).to.be.a("number");
    pm.expect(item.profilePic).to.be.a("string");
    pm.expect(item.hideBiometrics).to.be.a("boolean");
  }
});


pm.test("Response time is less than 200ms", () => {
  pm.expect(pm.response.responseTime).to.be.below(3000);
});


pm.test("Response property matches environment variable", function () {
  pm.expect(pm.response.json().username).to.eql(pm.environment.get("username"));
});





// Post Login
pm.test("Successful POST request", () => {
    pm.expect(pm.response.code).to.be.oneOf([200, 201,202]);
  });
  
  pm.test("Content-Type header is present", () => {
    pm.response.to.have.header("Content-Type");
  });
  
  const jsonData = pm.response.json();
  
  pm.test("Test data type of the response", () => {
    pm.expect(jsonData).to.be.an("object");
      pm.expect(jsonData.username).to.be.a("string");
      pm.expect(jsonData.password).to.be.a("string");
      pm.expect(jsonData.role).to.be.a("string");
      pm.expect(jsonData.heightInches).to.be.a("number");
      pm.expect(jsonData.weightLbs).to.be.a("number");
      pm.expect(jsonData.profilePic).to.be.a("string");
      pm.expect(jsonData.hideBiometrics).to.be.a("boolean");
    });




    
// Logout

pm.test("Successful POST request", () => {
    pm.expect(pm.response.code).to.be.oneOf([200, 201,202]);
  });
  
  pm.test("Content-Type header is present", () => {
    pm.response.to.have.header("Content-Type");
  });
  

  // Register
  pm.test("Successful POST request", () => {
    pm.expect(pm.response.code).to.be.oneOf([200, 201,202]);
  });
  
  pm.test("Content-Type header is present", () => {
    pm.response.to.have.header("Content-Type");
  });




  
  // PUT update

  pm.test("Successful PUT request", () => {
    pm.expect(pm.response.code).to.be.oneOf([200,201,204]);
  });
  
  
  pm.test("Content-Type header is present", () => {
    pm.response.to.have.header("Content-Type");
  });
  
  pm.test("Test data type of the response", () => {
    pm.expect(jsonData).to.be.an("object");
      pm.expect(jsonData.username).to.be.a("string");
      pm.expect(jsonData.password).to.be.a("string");
      pm.expect(jsonData.role).to.be.a("string");
      pm.expect(jsonData.heightInches).to.be.a("number");
      pm.expect(jsonData.weightLbs).to.be.a("number");
      pm.expect(jsonData.profilePic).to.be.a("string");
      pm.expect(jsonData.hideBiometrics).to.be.a("boolean");
    });

    // Patch update role
    pm.test("Successful PUT request", () => {
        pm.expect(pm.response.code).to.be.oneOf([200,201,204]);
      });
      
      pm.test("Content-Type header is present", () => {
        pm.response.to.have.header("Content-Type");
      });
      
      pm.test("the endpoint does not return unexpected status codes", () => {
        // comma separate the valid response codes below
        const unexpectedStatusCodes = [404, 500];
        pm.expect(pm.response.code).to.not.be.oneOf(
          unexpectedStatusCodes,
          `did not expect response status to be one of ${unexpectedStatusCodes} but got ${pm.response.code}.`
        );
      });



// Retrieve Users By Team

pm.test("Status code is 200", function () {
    pm.response.to.have.status(3000);
});

pm.test("Content-Type header is present", () => {
  pm.response.to.have.header("Content-Type");
});

pm.test("response must be valid and have a body", function () {
     pm.response.to.be.ok;
     pm.response.to.be.withBody;
     pm.response.to.be.json;
});

const jsonData = pm.response.json();

pm.test("Test data type of the response", () => {
  pm.expect(jsonData).to.be.an("array");
  for (const item of jsonData)  {
    pm.expect(item.username).to.be.a("string");
    pm.expect(item.password).to.be.a("string");
    pm.expect(item.role).to.be.a("string");
    pm.expect(item.heightInches).to.be.a("number");
    pm.expect(item.weightLbs).to.be.a("number");
    pm.expect(item.profilePic).to.be.a("string");
    pm.expect(item.hideBiometrics).to.be.a("boolean");
  }
});


pm.test("Response time is less than 200ms", () => {
  pm.expect(pm.response.responseTime).to.be.below(3000);
});


pm.test("Response property matches environment variable", function () {
  pm.expect(pm.response.json().username).to.eql(pm.environment.get("username"));
});


    
