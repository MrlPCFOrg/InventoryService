import com.oms.inventory.model.InventoryItem
import com.oms.inventory.model.InventoryProduct
import com.oms.inventory.model.InventoryRequest
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class InventoryCRUDAccSpec extends Specification {

    @Shared
    RESTClient restClient

    @Shared
    String localhost = "http://localhost:9099/inventory"

    def setupSpec() {
        restClient = new RESTClient(localhost)
    }

    def '/inventory/add - add the products into the inventory'() {
        given:
        def inventoryProductList = [
                new InventoryProduct(id: '59ccdbbf8b37e93ba0d01261', count: 0, productDisplayName: 'Product1', description: 'Sample Product', price: 100, threshold: 0 ),
                new InventoryProduct(id: '59ccdbbf8b37e93ba0d01262', count: 0, productDisplayName: 'Product2', description: 'Sample Product2',price: 200, threshold: 0 )]

        when:
        HttpResponseDecorator addProdResponse = restClient.post(
                path: "/inventory/addProduct",
                contentType: 'application/json',
                body: inventoryProductList)

        then:
        addProdResponse.status ==200
        addProdResponse.responseData[0].count == 0
        addProdResponse.responseData[1].count == 0

        when:
        def inventoryItemList = new InventoryRequest(inventoryItemList: [new InventoryItem(productId: '59ccdbbf8b37e93ba0d01261',threshold: 100, count: 5),
                                                                         new InventoryItem(productId: '59ccdbbf8b37e93ba0d01262',threshold: 200, count: 6)])
        addProdResponse = restClient.post(
                path: "/inventory/add",
                contentType: 'application/json',
                body: inventoryItemList)

        then:
        addProdResponse.status == 200
        addProdResponse.responseData.inventoryResponse[0].count == 5
        addProdResponse.responseData.inventoryResponse[1].count == 5

    }
}
