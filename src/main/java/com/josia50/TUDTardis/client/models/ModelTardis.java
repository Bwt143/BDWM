package com.josia50.TUDTardis.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTardis extends ModelBase {
	private final ModelRenderer tardis;
	private final ModelRenderer base;
	private final ModelRenderer posts;
	private final ModelRenderer door;
	private final ModelRenderer sign;
	private final ModelRenderer sign2;
	private final ModelRenderer sign3;
	private final ModelRenderer sign4;
	private final ModelRenderer roof;
	private final ModelRenderer lantern;

	public ModelTardis() {
		textureWidth = 256;
		textureHeight = 256;

		tardis = new ModelRenderer(this);
		tardis.setRotationPoint(0.0F, 24.0F, 0.0F);

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 0.0F, 0.0F);
		tardis.addChild(base);
		base.cubeList.add(new ModelBox(base, 0, 21, -10.0F, -1.0F, -10.0F, 20, 1, 20, 0.0F, false));

		posts = new ModelRenderer(this);
		posts.setRotationPoint(0.0F, 0.0F, 0.0F);
		tardis.addChild(posts);
		posts.cubeList.add(new ModelBox(posts, 60, 91, 8.4F, -29.0F, 8.4F, 1, 28, 1, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 56, 91, -9.6F, -29.0F, 8.4F, 1, 28, 1, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 64, 91, -9.6F, -27.0F, 8.4F, 1, 26, 1, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 52, 91, -9.6F, -29.0F, -9.6F, 1, 28, 1, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 48, 91, 8.4F, -29.0F, -9.6F, 1, 28, 1, 0.0F, false));

		door = new ModelRenderer(this);
		door.setRotationPoint(0.0F, 0.0F, 0.0F);
		tardis.addChild(door);
		door.cubeList.add(new ModelBox(door, 44, 91, 7.4F, -29.0F, 8.0F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 4, 89, 6.4F, -29.0F, 7.8F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 85, 93, -0.6F, -26.0F, 7.8F, 1, 25, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 89, -7.6F, -29.0F, 7.8F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 81, 90, 8.0F, -29.0F, 7.4F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 77, 90, 8.0F, -29.0F, -8.6F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 28, 89, 7.8F, -29.0F, -7.6F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 97, 97, 7.8F, -26.0F, -0.6F, 1, 25, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 75, 4, 7.8F, -2.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 109, 109, 7.6F, -7.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 107, 87, 7.6F, -13.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 104, 0, 7.6F, -19.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 103, 74, 7.6F, -25.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 103, 23, 7.6F, -25.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 102, 40, 7.6F, -19.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 101, 101, 7.6F, -13.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 98, 12, 7.6F, -7.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 90, 4, -8.8F, -7.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 89, 74, -8.8F, -19.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 73, 76, -8.8F, -25.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 54, 42, -8.8F, -25.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 42, -8.8F, -19.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 21, -8.8F, -13.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 0, -8.8F, -7.0F, -6.6F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 89, 18, -8.8F, -13.0F, 0.4F, 1, 5, 6, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 43, 63, -9.0F, -2.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 28, 75, 7.8F, -8.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 28, 61, -9.0F, -8.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 75, 7.8F, -14.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 61, -9.0F, -14.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 74, 74, 7.8F, -20.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 60, 21, -9.0F, -20.0F, -6.6F, 1, 1, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 60, 0, 7.8F, -29.0F, -6.6F, 1, 4, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 59, 59, -9.0F, -29.0F, -6.6F, 1, 4, 13, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 24, 89, 7.8F, -29.0F, 6.4F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 73, 90, 7.4F, -29.0F, -9.2F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 20, 89, 6.4F, -29.0F, -9.0F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 16, 89, -7.6F, -29.0F, -9.0F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 93, 93, -0.6F, -26.0F, -9.0F, 1, 25, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 88, 36, -6.6F, -2.0F, -9.0F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 116, 46, -6.6F, -7.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 116, 33, -6.6F, -13.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 115, 85, -6.6F, -19.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 114, 65, 0.4F, -25.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 112, 0, 0.4F, -19.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 111, 74, 0.4F, -13.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 111, 22, 0.4F, -7.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 110, 40, 0.4F, -7.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 109, 98, 0.4F, -13.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 106, 11, 0.4F, -25.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 58, 63, -6.6F, -25.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 53, -6.6F, -19.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 32, -6.6F, -13.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 0, 11, -6.6F, -7.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 102, 51, 0.4F, -19.0F, 7.6F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 114, 59, -6.6F, -25.0F, -8.8F, 6, 5, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 75, 32, -6.6F, -2.0F, 7.8F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 87, 72, -6.6F, -8.0F, -9.0F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 75, 2, -6.6F, -8.0F, 7.8F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 87, 34, -6.6F, -14.0F, -9.0F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 75, 0, -6.6F, -14.0F, 7.8F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 80, 40, -6.6F, -20.0F, -9.0F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 43, 61, -6.6F, -20.0F, 7.8F, 13, 1, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 85, 88, -6.6F, -29.0F, -9.0F, 13, 4, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 60, 35, -6.6F, -29.0F, 7.8F, 13, 4, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 69, 90, -8.6F, -29.0F, -9.2F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 40, 89, -9.2F, -29.0F, -8.6F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 36, 89, -9.2F, -29.0F, 7.4F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 12, 89, -9.0F, -29.0F, 6.4F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 89, 93, -9.0F, -26.0F, -0.6F, 1, 25, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 8, 89, -9.0F, -29.0F, -7.6F, 1, 28, 1, 0.0F, false));
		door.cubeList.add(new ModelBox(door, 32, 89, -8.6F, -29.0F, 8.0F, 1, 28, 1, 0.0F, false));

		sign = new ModelRenderer(this);
		sign.setRotationPoint(-0.1F, 0.0F, 1.0F);
		tardis.addChild(sign);
		sign.cubeList.add(new ModelBox(sign, 14, 47, 6.0F, -29.0F, -11.0F, 1, 4, 1, 0.0F, false));
		sign.cubeList.add(new ModelBox(sign, 12, 42, -7.0F, -29.0F, -11.0F, 1, 4, 1, 0.0F, false));
		sign.cubeList.add(new ModelBox(sign, 88, 67, -6.0F, -26.0F, -11.0F, 12, 1, 1, 0.0F, false));
		sign.cubeList.add(new ModelBox(sign, 88, 62, -6.0F, -28.0F, -10.6F, 12, 2, 1, 0.0F, false));
		sign.cubeList.add(new ModelBox(sign, 88, 65, -6.0F, -29.0F, -11.0F, 12, 1, 1, 0.0F, false));

		sign2 = new ModelRenderer(this);
		sign2.setRotationPoint(-0.1F, 0.0F, -1.2F);
		tardis.addChild(sign2);
		sign2.cubeList.add(new ModelBox(sign2, 8, 42, 6.0F, -29.0F, 10.0F, 1, 4, 1, 0.0F, false));
		sign2.cubeList.add(new ModelBox(sign2, 0, 42, -7.0F, -29.0F, 10.0F, 1, 4, 1, 0.0F, false));
		sign2.cubeList.add(new ModelBox(sign2, 88, 38, -6.0F, -26.0F, 10.0F, 12, 1, 1, 0.0F, false));
		sign2.cubeList.add(new ModelBox(sign2, 88, 59, -6.0F, -28.0F, 9.6F, 12, 2, 1, 0.0F, false));
		sign2.cubeList.add(new ModelBox(sign2, 60, 18, -6.0F, -29.0F, 10.0F, 12, 1, 1, 0.0F, false));

		sign3 = new ModelRenderer(this);
		sign3.setRotationPoint(-1.2F, 0.0F, -0.1F);
		tardis.addChild(sign3);
		sign3.cubeList.add(new ModelBox(sign3, 14, 35, 10.0F, -29.0F, -7.0F, 1, 4, 1, 0.0F, false));
		sign3.cubeList.add(new ModelBox(sign3, 14, 30, 10.0F, -29.0F, 6.0F, 1, 4, 1, 0.0F, false));
		sign3.cubeList.add(new ModelBox(sign3, 44, 78, 10.0F, -26.0F, -6.0F, 1, 1, 12, 0.0F, false));
		sign3.cubeList.add(new ModelBox(sign3, 59, 76, 9.6F, -28.0F, -6.0F, 1, 2, 12, 0.0F, false));
		sign3.cubeList.add(new ModelBox(sign3, 15, 75, 10.0F, -29.0F, -6.0F, 1, 1, 12, 0.0F, false));

		sign4 = new ModelRenderer(this);
		sign4.setRotationPoint(1.0F, 0.0F, -0.1F);
		tardis.addChild(sign4);
		sign4.cubeList.add(new ModelBox(sign4, 14, 25, -11.0F, -29.0F, -7.0F, 1, 4, 1, 0.0F, false));
		sign4.cubeList.add(new ModelBox(sign4, 0, 21, -11.0F, -29.0F, 6.0F, 1, 4, 1, 0.0F, false));
		sign4.cubeList.add(new ModelBox(sign4, 74, 59, -11.0F, -26.0F, -6.0F, 1, 1, 12, 0.0F, false));
		sign4.cubeList.add(new ModelBox(sign4, 75, 18, -10.6F, -28.0F, -6.0F, 1, 2, 12, 0.0F, false));
		sign4.cubeList.add(new ModelBox(sign4, 15, 61, -11.0F, -29.0F, -6.0F, 1, 1, 12, 0.0F, false));

		roof = new ModelRenderer(this);
		roof.setRotationPoint(0.0F, 0.0F, 0.0F);
		tardis.addChild(roof);
		roof.cubeList.add(new ModelBox(roof, 0, 42, -9.0F, -31.0F, -9.0F, 18, 1, 18, 0.0F, false));
		roof.cubeList.add(new ModelBox(roof, 0, 0, -10.0F, -30.0F, -10.0F, 20, 1, 20, 0.0F, false));
		roof.cubeList.add(new ModelBox(roof, 54, 42, -8.0F, -32.0F, -8.0F, 16, 1, 16, 0.0F, false));

		lantern = new ModelRenderer(this);
		lantern.setRotationPoint(0.0F, 0.0F, 0.0F);
		tardis.addChild(lantern);
		lantern.cubeList.add(new ModelBox(lantern, 12, 15, -0.9F, -35.0F, -1.1F, 2, 3, 2, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 13, 9, 1.1F, -33.0F, -1.1F, 1, 1, 2, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 8, 0, -0.9F, -36.0F, -1.1F, 3, 1, 2, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 8, 23, -1.9F, -33.0F, 0.9F, 4, 1, 1, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 8, 3, -1.9F, -36.0F, 0.9F, 4, 1, 1, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 8, 21, -1.9F, -33.0F, -2.1F, 4, 1, 1, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 0, 17, -1.9F, -36.0F, -2.1F, 4, 1, 1, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 0, 3, -1.9F, -36.0F, -1.1F, 1, 1, 2, 0.0F, false));
		lantern.cubeList.add(new ModelBox(lantern, 0, 0, -1.9F, -33.0F, -1.1F, 1, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		tardis.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}