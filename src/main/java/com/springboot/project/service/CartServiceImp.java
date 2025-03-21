package com.springboot.project.service;

import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Food;
import com.springboot.project.model.User;
import com.springboot.project.repository.CategoryRepository;
import com.springboot.project.repository.FoodRepository;
import com.springboot.project.repository.cartItemRepo;
import com.springboot.project.repository.cartRepository;
import com.springboot.project.request.AddCardItemReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{
    @Autowired
    private cartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private cartItemRepo cartItemRepo;
    @Autowired
    private FoodService foodService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CartItem addItemToCart(AddCardItemReq req, String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Food food=foodService.findFoodById(req.getFoodId());
        Cart cart= cartRepository.findByCustomerId(user.getId());
        for (CartItem cartItem :cart.getItems()){
            if (cartItem.getFood().equals(food)){
                int newQuantity =cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }
        CartItem newCartItem  = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());
        CartItem savedCartItem = cartItemRepo.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional=cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);
        return cartItemRepo.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart= cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItemOptional=cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem item=cartItemOptional.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;
        for (CartItem cartItem:cart.getItems()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception("Cart Not Found With Id"+id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart=cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotal(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartById(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
