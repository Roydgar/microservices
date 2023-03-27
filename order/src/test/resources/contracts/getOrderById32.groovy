import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should get existing order by id"
    request{
        method GET()
        url("/api/v1/orders/77e5e74f-6e2d-4a25-bcfb-30f4c550ec8f") {
        }
    }
    response {
        body("")
        status 200
    }
}