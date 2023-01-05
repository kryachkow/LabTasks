package com.store.service;

import com.store.strategy.BookGenerationStrategy;

public interface BookGeneratingStrategyService {

  BookGenerationStrategy getStrategy();

}
