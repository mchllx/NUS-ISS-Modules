export interface Inventory {
    id: string
    image: string
    description: string 
}

export interface Cart {
    id: number
    inven_id: string
    image: string
    description: string 
    quantity: number
}

// export interface Cart {
//     inventory: Inventory
//     quantity: number
// }
