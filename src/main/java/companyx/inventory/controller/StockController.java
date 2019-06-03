package companyx.inventory.controller;

import companyx.inventory.exception.ResourceNotFoundException;
import companyx.inventory.model.Stock;
import companyx.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StockController {
	@Autowired
	private StockRepository stockRepository;

	@GetMapping("/stocks")
	@CrossOrigin(origins="http://localhost:4200")
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	@GetMapping("/stocks/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));
		return ResponseEntity.ok().body(stock);
	}

	@PostMapping("/stocks")
	@CrossOrigin(origins="http://localhost:4200")
	public Stock createStock(@Valid @RequestBody Stock stock) {
		return stockRepository.save(stock);
	}

	@PutMapping("/stocks/{id}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Stock> updateStock(@PathVariable(value = "id") Long stockId,
			@Valid @RequestBody Stock stockDetails) throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));

		stock.setLoanedTo(stockDetails.getLoanedTo());
		stock.setDescription(stockDetails.getDescription());
		stock.setItem(stockDetails.getItem());
		final Stock updatedStock = stockRepository.save(stock);
		return ResponseEntity.ok(updatedStock);
	}

	@DeleteMapping("/stocks/{id}")
	@CrossOrigin(origins="http://localhost:4200")
	public Map<String, Boolean> deleteStock(@PathVariable(value = "id") Long stockId)
			throws ResourceNotFoundException {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock not found for this id :: " + stockId));

		stockRepository.delete(stock);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
